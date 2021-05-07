package com.ikite.seruvenn.CETypeClassMap.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ikite.seruvenn.CETypeClassMap.exception.CommandClassPairException;
import com.ikite.seruvenn.CETypeClassMap.exception.EventClassPairException;
import com.ikite.seruvenn.CETypeClassMap.model.CommandTypeClassName;
import com.ikite.seruvenn.CETypeClassMap.model.EventTypeClassName;
import com.ikite.seruvenn.CETypeClassMap.repository.CommandTypeClassNameRepository;
import com.ikite.seruvenn.CETypeClassMap.repository.EventTypeClassNameRepository;
import com.ikite.seruvenn.CETypeClassMap.service.IMessageTypeClassNameService;


@Service
@CacheConfig(cacheNames = {"messagetypeclassname"})
public class MessageTypeClassNameService implements IMessageTypeClassNameService{
	
	private static final Logger LOG = LoggerFactory.getLogger(MessageTypeClassNameService.class);
	
	@Autowired
	CommandTypeClassNameRepository commandTypeClassNameRepository;

	@Autowired
	EventTypeClassNameRepository eventTypeClassNameRepository;
	
	@Override
	public List<CommandTypeClassName> getCommandTypeClassName(String commandType) {
		
		//FIXME https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/cache/annotation/EnableCaching.html#annotation.type.element.detail
		//FIXME buradaki proxyTargetClass açıklaması okunmalı; çözüm burada gibi... şu an cache boş gibi her defasında metoda giriyor...
		
		LOG.info("*********db den ilk kez çekiliyor. CommandType: "+commandType);
		return commandTypeClassNameRepository.findByCommandType(commandType);
	}
	
	@Override
	@Cacheable
	public List<EventTypeClassName> getEventTypeClassName(String eventType) {
		LOG.info("*********db den ilk kez çekiliyor. EventType: "+eventType);
		return eventTypeClassNameRepository.findByEventType(eventType);
	}

	@Override
	public boolean equalsCommandClassPair(String commandType, String className) throws CommandClassPairException {
		
		List<CommandTypeClassName> pairs = getCommandTypeClassName(commandType);
		
		if (pairs == null || pairs.isEmpty()) {
			throw new CommandClassPairException("**** Command Type - ClassName pairs not found. CommandType: "+commandType);
		}
		
		if (pairs.size() > 1) {
			throw new CommandClassPairException("**** More than 1 Command Type - ClassName pair found - check pair DB");
		}
		
		CommandTypeClassName pair = pairs.get(0);
		
		if (pair.getClassName() == null || pair.getClassName().isEmpty() || pair.getCommandType() == null || pair.getCommandType().isEmpty()) {
			throw new CommandClassPairException("**** Dirty data on Command Type - ClassName pair - check pair DB. CommandType: "+commandType);
		}
		
		return pair.getClassName().equals(className);
	}
	
	@Override
	public boolean equalsEventClassPair(String eventType, String className) throws EventClassPairException {
		
		List<EventTypeClassName> pairs = getEventTypeClassName(eventType);
		
		if (pairs == null || pairs.isEmpty()) {
			throw new EventClassPairException("**** Event Type - ClassName pairs not found. EventType: "+eventType);
		}
		
		if (pairs.size() > 1) {
			throw new EventClassPairException("**** More than 1 Event Type - ClassName pair found - check pair DB. EventType: "+eventType );
		}
		
		EventTypeClassName pair = pairs.get(0);
		
		if (pair.getClassName() == null || pair.getClassName().isEmpty() || pair.getEventType() == null || pair.getEventType().isEmpty()) {
			throw new EventClassPairException("**** Dirty data on Event Type - ClassName pair - check pair DB. EventType: "+ eventType);
		}
		
		return pair.getClassName().equals(className);
	}


}
