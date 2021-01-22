package com.ikite.seruvenn.SagaOrchestrator.service.impl;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ikite.seruvenn.CETypeClassMap.service.IMessageTypeClassNameService;
import com.ikite.seruvenn.Commons.constants.CommonConstants;
import com.ikite.seruvenn.Commons.kafka.KafkaMessageInnerEntity;
import com.ikite.seruvenn.Commons.message.Message;
import com.ikite.seruvenn.Commons.message.MessageArchetype;
import com.ikite.seruvenn.SagaOrchestrator.eda.KafkaConfig;
import com.ikite.seruvenn.SagaOrchestrator.exception.CommandGenerationException;
import com.ikite.seruvenn.SagaOrchestrator.service.ICommandBuilderService;

@SuppressWarnings("rawtypes")
@Service
public class CommandBuilderService implements ICommandBuilderService{

	private static final Logger LOG = LoggerFactory.getLogger(CommandBuilderService.class);
	private Producer<String, Message> producer;
	
	@Autowired 
	IMessageTypeClassNameService messageTypeClassNameService;
	
	@Autowired
	KafkaConfig kafkaConfig;
	
	@Autowired
	Environment env;
	
	@PostConstruct
	public void setUp() {
		producer = kafkaConfig.producerFactory().createProducer();
		LOG.info("[[[[[[[[[[[[[[[************ EBS setup entered");;
	}

	/**
	 * sfdsfdsfd
	 * sdfsdfsdf
	 */
	@Override
	public <T extends KafkaMessageInnerEntity> String pushCommand(String key, String commandType, String sagaCode, T commandEntity) throws Exception {
		
		if (!messageTypeClassNameService.equalsCommandClassPair(commandType, commandEntity.getClass().getName())) {
			throw new CommandGenerationException("**** Command Type - Class not matched. Check suitable classname or command type");
			//FIXME property dosyasına alınmalı
		}
		
		producer.send(new ProducerRecord<String, Message>(
				env.getProperty(CommonConstants.TOPIC_NAME_STRING), //FIXME command topic adı gelecek
				key == null ? UUID.randomUUID().toString() : key, 
				new Message<T>(
						MessageArchetype.COMMAND.toString(),
						commandType.toString(), 
						0, 
						sagaCode, 
						commandEntity)));
		
		return CommonConstants.SUCCESS;
		
	}
	

	
}
