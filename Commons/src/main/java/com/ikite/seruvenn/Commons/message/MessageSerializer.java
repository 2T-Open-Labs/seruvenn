package com.ikite.seruvenn.Commons.message;

import java.util.Map;
import java.util.Objects;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("rawtypes")
public class MessageSerializer implements Serializer<Message>{

	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	public MessageSerializer() {
	}

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public byte[] serialize(String topic, Message data) {
		if (Objects.isNull(data)) {
			return null;
		}
		try {
			return objectMapper.writeValueAsBytes(data);
		} catch (Exception e) {
			throw new SerializationException("Error serializing message", e);
		}
	}

	@Override
	public void close() {
	}
	
}
