package com.ikite.seruvenn.CETypeClassMap.service;

import java.util.List;

import com.ikite.seruvenn.CETypeClassMap.exception.CommandClassPairException;
import com.ikite.seruvenn.CETypeClassMap.exception.EventClassPairException;
import com.ikite.seruvenn.CETypeClassMap.model.CommandTypeClassName;
import com.ikite.seruvenn.CETypeClassMap.model.EventTypeClassName;

public interface IMessageTypeClassNameService {

	List<CommandTypeClassName> getCommandTypeClassName(String commandType);

	List<EventTypeClassName> getEventTypeClassName(String eventType);

	boolean equalsCommandClassPair(String commandType, String className) throws CommandClassPairException;

	boolean equalsEventClassPair(String eventType, String className) throws EventClassPairException;


	
}
