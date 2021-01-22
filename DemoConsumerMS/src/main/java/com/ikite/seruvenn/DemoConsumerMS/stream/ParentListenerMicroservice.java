package com.ikite.seruvenn.DemoConsumerMS.stream;

import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.ikite.seruvenn.Commons.carriers.Foo;
import com.ikite.seruvenn.Commons.message.CommandType;
import com.ikite.seruvenn.Commons.message.EventType;
import com.ikite.seruvenn.Commons.message.Message;
import com.ikite.seruvenn.Commons.stream.impl.StreamBase;
import com.ikite.seruvenn.EventManager.services.IEventBuilderService;

@SuppressWarnings("rawtypes")
@Component
public class ParentListenerMicroservice extends StreamBase{

	private static final Logger LOG = LoggerFactory.getLogger(ParentListenerMicroservice.class);
	
	@Autowired
	IEventBuilderService eventBuilderService;

	@Autowired
	public ParentListenerMicroservice(Environment env) {
		super(env);
	}
	
	@Override
	public KStream<String, Message> buildForwardStream() {
			
		//success stream
		return forwardCommandStream.map((key, command) -> {
			
			//TODO business code buraya girilecek
			
			try {
				return eventBuilderService.buildNewStreamEvent(key, EventType.PARENT_SUCCESSFUL.toString(), command.getSagaCode(), new Foo());
			} catch (Exception e) {
				LOG.error(e.getMessage());
				return null;
			}
			
		});
		
	}
	
	@Override
	public KStream<String, Message> buildReverseStream() {
		
		//fail stream
		return reverseCommandStream.map((key, command) -> {
			
			//TODO ters business code buraya girilecek
			
			try {
				return eventBuilderService.buildNewStreamEvent(key, EventType.PARENT_ROLLBACK_OK.toString(), command.getSagaCode(), new Foo());
			} catch (Exception e) {
				LOG.error(e.getMessage());
				return null;
			}
			
		});
		
	}

	@Override
	protected String getStreamApplicationId() {
		return "parent_micro_stream";
	}

	@Override
	protected CommandType getIncomingForwardCommandType() {
		// TODO Auto-generated method stub
		return CommandType.PARENT_COMMAND;
	}

	@Override
	protected CommandType getIncomingRollbackCommandType() {
		// TODO Auto-generated method stub
		return CommandType.PARENT_ROLLBACK_COMMAND;
	}

}