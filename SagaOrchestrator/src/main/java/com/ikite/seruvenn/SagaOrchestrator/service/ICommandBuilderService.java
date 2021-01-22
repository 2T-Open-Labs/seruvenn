package com.ikite.seruvenn.SagaOrchestrator.service;

import com.ikite.seruvenn.Commons.kafka.KafkaMessageInnerEntity;

public interface ICommandBuilderService {

	<T extends KafkaMessageInnerEntity> String pushCommand(String key, String commandType, String sagaCode, T commandEntity) throws Exception;

}
