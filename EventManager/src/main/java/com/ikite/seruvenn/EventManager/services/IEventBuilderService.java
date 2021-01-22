package com.ikite.seruvenn.EventManager.services;

import org.apache.kafka.streams.KeyValue;

import com.ikite.seruvenn.Commons.kafka.KafkaMessageInnerEntity;
import com.ikite.seruvenn.Commons.message.Message;

public interface IEventBuilderService {

	<T extends KafkaMessageInnerEntity> KeyValue<String, Message<T>> buildNewStreamEvent(String key, String eventType,
			String sagaCode, T eventEntity) throws Exception;

}
