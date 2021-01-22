package com.ikite.seruvenn.SagaOrchestrator.service;

import com.ikite.seruvenn.Commons.kafka.KafkaMessageInnerEntity;
import com.ikite.seruvenn.SagaOrchestrator.exception.CommandGenerationException;
import com.ikite.seruvenn.SagaOrchestrator.exception.SagaDefinitionException;

public interface ISagaOrchestratorService {

	<T extends KafkaMessageInnerEntity> String startSaga(String sagaCode, T commandEntity) throws SagaDefinitionException, CommandGenerationException;

	
	
}
