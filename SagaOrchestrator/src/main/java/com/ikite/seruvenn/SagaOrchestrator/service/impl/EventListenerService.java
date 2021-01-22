package com.ikite.seruvenn.SagaOrchestrator.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ikite.seruvenn.Commons.carriers.Joined;
import com.ikite.seruvenn.Commons.constants.CommonConstants;
import com.ikite.seruvenn.Commons.message.EventType;
import com.ikite.seruvenn.Commons.message.Message;
import com.ikite.seruvenn.Commons.message.MessageArchetype;
import com.ikite.seruvenn.Commons.stream.impl.SagaStreamBase;
import com.ikite.seruvenn.SagaOrchestrator.service.ICommandBuilderService;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class EventListenerService extends SagaStreamBase {
	
	private static final Logger LOG = LoggerFactory.getLogger(EventListenerService.class);

	@Autowired
	ICommandBuilderService commandBuilderService;
	
	@Autowired
	SagaStateBean sagaStateBean;

	@Autowired
	public EventListenerService(Environment env) {
		super(env);
	}
	
	@Override
	public void buildStream() {
		
		KStream<String, Message> source = builder.stream(env.getProperty(CommonConstants.TOPIC_NAME_STRING));
		
		List<KStream<String, Message>> branches = new ArrayList<KStream<String, Message>>();
		
		for (EventType targetEventType : getSuccessfulEvents()) {
			Predicate<String, Message> pred = (key, event) -> isThatEventType(targetEventType, event);
			branches.add(source.filter(pred));
		}
		branches.add(source.filter((key, event) -> isThatEventType(EventType.GENERIC_ROLLBACK, event)));
		
		// listenin son stream'ini bir kenara ayırıyoruz
		// çünkü sondaki generic hata event'i, görmek için EventType enumuna bakabilirsiniz
		// tüm event tiplerini bu generic hata event'i ile left joinleyerek 
		// doğru çağrımlarda gerekli command'in atılmasını
		// hata durumlarında ise gelen çapraz tuple (std event + hata eventi) ile komple rollback sağlanmış olacak
		
		int genericErrorStreamPosition = branches.size() - 1; 
		
		KStream<String, Message>[] allOptionList = new KStream[branches.size()];
		for (int i = 0; i < genericErrorStreamPosition; i++) {
			allOptionList[i] = branches.get(i).leftJoin(branches.get(genericErrorStreamPosition), 
									(standartEvent, errorEvent) -> 
										new Message<Joined>(
													MessageArchetype.EVENT.toString(),
													standartEvent.getMessageType(),
													0, 
													standartEvent.getSagaCode(),
													new Joined(standartEvent, errorEvent)
											   )
									, JoinWindows.of(3000L)
								);
			allOptionList[i].foreach((key, event) -> {
				Joined joinedEventCouple = (Joined) event.getCarrierEntity();
				if (joinedEventCouple.getJoiningEvent() == null) {
					List<String> forwardDommandsToRun = sagaStateBean.getForwardCommandListFrom(joinedEventCouple.getPrimaryEvent().getSagaCode(), joinedEventCouple.getPrimaryEvent().getMessageType());
					for (String command : forwardDommandsToRun) {
						try {
							commandBuilderService.pushCommand(
									key,
									command, 
									joinedEventCouple.getPrimaryEvent().getSagaCode(),
									joinedEventCouple.getPrimaryEvent().getCarrierEntity());
						} catch (Exception e) {
							LOG.error(e.getMessage());
							//FIXME burası düzgün bir formata koyulabilir, Log'a dönen mesajın detayı içerdiğinden emin olmak lazım.
							//Çünkü burası Stream'in akmaya devam etmesini tehdit etmeden, hata bilgisini aktarabilmeli...
						}
					}
				} 
				else {
					if (isThatEventType(EventType.GENERIC_ROLLBACK, joinedEventCouple.getJoiningEvent())) {
						//FIXME reverse action ların göndeirlmesi lazım
						//push new Rollback_command();
						String reverseCommandToRun = sagaStateBean.getReverseCommandListFrom(
								joinedEventCouple.getPrimaryEvent().getSagaCode(), 
								joinedEventCouple.getPrimaryEvent().getMessageType());
						
						if (reverseCommandToRun != null) { 
							//FIXME sagaStateBean.getReverseCommandListFrom exception tanımlandığında bu kalkabilir
							try {
								commandBuilderService.pushCommand(
										key,
										reverseCommandToRun, 
										joinedEventCouple.getPrimaryEvent().getSagaCode(),
										joinedEventCouple.getPrimaryEvent().getCarrierEntity());
							} catch (Exception e) {
								LOG.error(e.getMessage());
								//FIXME burası düzgün bir formata koyulabilir, Log'a dönen mesajın detayı içerdiğinden emin olmak lazım.
								//Çünkü burası Stream'in akmaya devam etmesini tehdit etmeden, hata bilgisini aktarabilmeli...
							}
							
						}
					}
					
				}
			});
		}
		
		/**
		 * 
		 * X_successful_event + generic_rollback = (SagaOrc) = X_reverse_command
		 * Y_successful_event + generic_rollback = (SagaOrc) = Y_reverse_command
		 * 
		 * mikroservislerde: success ve fail stream'leri ayrı olacak
		 * örneğin fail'de Y_reverse_command geldiyse tersinir işini yapacak
		 * Y_command geldiyse de standart, success path işini yapacak
		 * 
		 * rollback başlamışsa bir süre sonra zincirdeki command ler kesilecek
		 * ve akış duracak. Sadece çalışmış servislerin join ile rollback leri 
		 * çalışacak ama başlayan mikro akışa dokunmayacağız... bitmesi ve 
		 * devamında rollback'inin de arkasıra çalışmasını bekleyeceğiz. 
		 * 
		 */
			
	}

	private EventType[] getSuccessfulEvents() {
		
		List<EventType> result = new ArrayList<EventType>();
		
		for (EventType eventType : EventType.values()) {
			//FIXME buraya farklı bir mantık gelebilir, geçici olarak eklendi.
			//FIXME başarılı olanların hepsinde SUCCESSFUL olmalı mantığından hareketle yazıldı.
			if (eventType.getFieldDescription().contains("SUCCESSFUL")) {
				result.add(eventType);
			}
		}
		
		return result.toArray(new EventType[result.size()]);
	}

	private boolean isThatEventType(EventType targetEventType, Message event) {
		
		return targetEventType.toString().equals(event.getMessageType()) &&
			   event.getArchetype().equals(MessageArchetype.EVENT.toString());
		
	}

	@Override
	protected String getStreamApplicationId() {
		return "saga_orc_listener_stream";
	}

}
