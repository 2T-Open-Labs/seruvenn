package com.ikite.seruvenn.Commons.message;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@SuppressWarnings({"rawtypes", "unchecked"})
public class MessageSerde implements Serde<Message>{

	private Serializer<Message> serializer;
	private Deserializer<Message> deserializer;
	
	public MessageSerde() {
		this.serializer = new MessageSerializer();
		this.deserializer = new MessageDeserializer();
	}
	
	@Override
	public void configure(Map configs, boolean isKey) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void close() {
		this.serializer.close();
		this.deserializer.close();
	}
	
	@Override
	public Serializer serializer() {
		return this.serializer;
	}
	
	@Override
	public Deserializer deserializer() {
		return this.deserializer;
	}
	
}
