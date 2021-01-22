package com.ikite.seruvenn.Commons.stream.impl;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import javax.annotation.PostConstruct;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.core.env.Environment;

import com.ikite.seruvenn.Commons.constants.CommonConstants;
import com.ikite.seruvenn.Commons.message.CommandType;
import com.ikite.seruvenn.Commons.message.Message;
import com.ikite.seruvenn.Commons.message.MessageArchetype;
import com.ikite.seruvenn.Commons.message.MessageSerde;
import com.ikite.seruvenn.Commons.stream.IStreamBase;

@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class StreamBase implements IStreamBase{

	private StreamsBuilder builder;
	protected KStream<String, Message> forwardCommandStream;
	protected KStream<String, Message> reverseCommandStream;
	protected Environment env;
	
	public StreamBase(Environment env) {
		super();
		this.env = env;
	}

	@PostConstruct
	public void setUp() {
		
		builder = new StreamsBuilder();
		
		KStream<String, Message> source = builder.stream(env.getProperty(CommonConstants.TOPIC_NAME_STRING));
		KStream<String, Message>[] branches = source.branch(
						(key, command) -> isThatCommandType(command),
				  	  	(key, command) -> isRollbackType(command));
		
		forwardCommandStream = branches[0];
		reverseCommandStream = branches[1];
		
		buildForwardStream().to(env.getProperty(CommonConstants.TOPIC_NAME_STRING));
		buildReverseStream().to(env.getProperty(CommonConstants.TOPIC_NAME_STRING));
		
		startStreamLifecycle();
		
	}

	private void startStreamLifecycle() {
		final Topology topology = builder.build();
		 
        final KafkaStreams streams = new KafkaStreams(topology, getProperties());
        final CountDownLatch latch = new CountDownLatch(1);
 
        try {
            streams.start();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> streams.close()));
        } catch (Throwable e) {
        	streams.close();
        } finally {
			if(latch!=null)
				latch.countDown();
		}
        
        try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	
	public Properties getProperties() {
		
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, getStreamApplicationId());
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty(CommonConstants.KAFKA_BOOTSTRAP_SERVERS));
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, MessageSerde.class);
		props.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE);
//		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 6000);
		
		return props;
		
	}
	
	protected boolean isThatCommandType(Message command) {
		
		return command.getMessageType().equals(getIncomingForwardCommandType().toString()) &&
			   command.getArchetype().equals(MessageArchetype.COMMAND.toString());
		
	}
	
	protected boolean isRollbackType(Message command) {
		
		//TODO db den çekilebilir, nasıl: sagaState ler arasında reverse=1 olan kayıt var ise..
		return command.getMessageType().equals(getIncomingRollbackCommandType().toString()) &&
			   command.getArchetype().equals(MessageArchetype.COMMAND.toString());
		
	}
	
	protected abstract String getStreamApplicationId();
	
	protected abstract CommandType getIncomingForwardCommandType();
	
	protected abstract CommandType getIncomingRollbackCommandType();
	
}
