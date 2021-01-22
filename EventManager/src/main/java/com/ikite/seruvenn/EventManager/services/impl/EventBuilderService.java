package com.ikite.seruvenn.EventManager.services.impl;

import javax.annotation.PostConstruct;

import org.apache.kafka.streams.KeyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ikite.seruvenn.CETypeClassMap.service.IMessageTypeClassNameService;
import com.ikite.seruvenn.Commons.kafka.KafkaMessageInnerEntity;
import com.ikite.seruvenn.Commons.message.Message;
import com.ikite.seruvenn.Commons.message.MessageArchetype;
import com.ikite.seruvenn.EventManager.services.IEventBuilderService;

@SuppressWarnings({})
@Service
public class EventBuilderService implements IEventBuilderService{

	private static final Logger LOG = LoggerFactory.getLogger(EventBuilderService.class);
	
	@Autowired 
	IMessageTypeClassNameService messageTypeClassNameService;
	
	@Autowired
	Environment env;
	
	@PostConstruct
	public void setUp() {
		LOG.info("[[[[[[[[[[[[[[[************ EBS setup entered");;
	}

	@Override
	public <T extends KafkaMessageInnerEntity> KeyValue<String, Message<T>> buildNewStreamEvent(String key, String eventType, String sagaCode, T eventEntity) throws Exception {
		
		if (!messageTypeClassNameService.equalsEventClassPair(eventType, eventEntity.getClass().getName())) {
			throw new Exception("**** Event Type - Class not matched. Check suitable classname or event type");
		}
		
		return new KeyValue<String, Message<T>>(
				key, 
				new Message<T>(MessageArchetype.EVENT.toString(), 
				eventType.toString(), 
				0, 
				sagaCode, 
				eventEntity));
	}	

	
}
