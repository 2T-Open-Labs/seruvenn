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
public class Sub2ListenerMicroservice extends StreamBase {

	private static final Logger LOG = LoggerFactory.getLogger(Sub2ListenerMicroservice.class);

	@Autowired
	IEventBuilderService eventBuilderService;

	@Autowired
	public Sub2ListenerMicroservice(Environment env) {
		super(env);
	}

	@Override
	public KStream<String, Message> buildForwardStream() {

		//success stream
		return forwardCommandStream.map((key, command) -> { 

			//TODO business code buraya girilecek
			
			try {
				return eventBuilderService.buildNewStreamEvent(key, EventType.SUB2_SUCCESSFUL.toString(), command.getSagaCode(),
						new Foo());
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
				return eventBuilderService.buildNewStreamEvent(key, EventType.SUB2_ROLLBACK_OK.toString(), command.getSagaCode(),
						new Foo());
			} catch (Exception e) {
				LOG.error(e.getMessage());
				return null;
			}

		});

	}

	@Override
	protected String getStreamApplicationId() {
		return "sub2_micro_stream";
	}

	@Override
	protected CommandType getIncomingForwardCommandType() {
		return CommandType.SUB2_COMMAND;
	}
	
	@Override
	protected CommandType getIncomingRollbackCommandType() {
		return CommandType.SUB2_ROLLBACK_COMMAND;
	}

}
