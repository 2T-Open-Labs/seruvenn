package com.ikite.seruvenn.Commons.message;


import java.util.Date;
import java.util.Map;
import java.util.Objects;

import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.util.ClassUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikite.seruvenn.Commons.kafka.KafkaMessageInnerEntity;

@SuppressWarnings({"rawtypes", "unchecked"})
public class MessageDeserializer implements Deserializer<Message>{

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.enableDefaultTyping();

	}
	
	public MessageDeserializer() {
	}

	@Override
	public Message deserialize(String topic, byte[] bytes) {
		if (Objects.isNull(bytes)) {
			return null;
		}
		
		Message data = new Message();
		
		try {
			JsonNode commandNode = objectMapper.readTree(bytes);
			data.setId(commandNode.get("id").asText());
			data.setTransactionId(commandNode.get("transactionId").asText());
			data.setArchetype(commandNode.get("archetype").asText());
			data.setMessageType(commandNode.get("messageType").asText());
			data.setCreationDate(objectMapper.convertValue(commandNode.get("creationDate"), Date.class));
			data.setSagaCode(commandNode.get("sagaCode").asText());
			
			JsonNode commandEntityNode = commandNode.get("carrierEntity");
			JsonNode classAlias = commandEntityNode.get("@class");

			if (classAlias == null) {
				throw new Exception("**** Class Alias not defined");
			}
			
			Class commandEntityClass = ClassUtils.forName(classAlias.asText(), null);
			data.setCarrierEntity((KafkaMessageInnerEntity) objectMapper.convertValue(commandEntityNode, commandEntityClass));
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}
		
		return data;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}
	
}
