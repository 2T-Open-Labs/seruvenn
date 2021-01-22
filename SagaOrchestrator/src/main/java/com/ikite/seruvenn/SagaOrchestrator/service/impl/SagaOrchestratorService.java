package com.ikite.seruvenn.SagaOrchestrator.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikite.seruvenn.Commons.constants.CommonConstants;
import com.ikite.seruvenn.Commons.kafka.KafkaMessageInnerEntity;
import com.ikite.seruvenn.SagaOrchestrator.exception.CommandGenerationException;
import com.ikite.seruvenn.SagaOrchestrator.exception.ExceptionConstants;
import com.ikite.seruvenn.SagaOrchestrator.exception.SagaDefinitionException;
import com.ikite.seruvenn.SagaOrchestrator.model.Saga;
import com.ikite.seruvenn.SagaOrchestrator.model.SagaState;
import com.ikite.seruvenn.SagaOrchestrator.repository.ISagaRepository;
import com.ikite.seruvenn.SagaOrchestrator.service.ICommandBuilderService;
import com.ikite.seruvenn.SagaOrchestrator.service.ISagaOrchestratorService;

@Service
public class SagaOrchestratorService implements ISagaOrchestratorService{

	private static final Logger LOG = LoggerFactory.getLogger(SagaOrchestratorService.class);
	
	@Autowired
	ICommandBuilderService commandBuilderService;
	
	@Autowired
	ISagaRepository sagaRepository;
	
	@Autowired
	SagaStateBean sagaStateBean;
	
	@Override
	public <T extends KafkaMessageInnerEntity> String startSaga(String sagaCode, T commandEntity) throws SagaDefinitionException, CommandGenerationException {
		
		List<Saga> saga = sagaRepository.findByKod(sagaCode);
		//FIXME buraya tek kayıt gelecek şekilde düzenlemeli
		
		if (saga == null || saga.isEmpty()) {
			throw new SagaDefinitionException(ExceptionConstants.SAGA_TANIMI_BULUNAMADI);
		}
		
		Saga currentSaga = saga.get(0);
		sagaStateBean.addToSagaList(currentSaga); //FIXME varsa kontrolü ???
		
		if (currentSaga.getStateList().isEmpty()) {
			throw new SagaDefinitionException(ExceptionConstants.SAGA_STATE_TANIMI_BULUNAMADI);
		}
		
		SagaState firstState = currentSaga.getStateList().iterator().next();
		
		try {
			LOG.info("*****-------*********");
			commandBuilderService.pushCommand(null, firstState.getCommandType(), currentSaga.getKod(), commandEntity);
		} catch (Exception e) {
			throw new CommandGenerationException(e.getMessage());
		}
		
		return CommonConstants.SUCCESS;
	}
	
}
