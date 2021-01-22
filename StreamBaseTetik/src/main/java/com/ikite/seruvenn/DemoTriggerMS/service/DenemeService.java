package com.ikite.seruvenn.DemoTriggerMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikite.seruvenn.Commons.carriers.Foo;
import com.ikite.seruvenn.Commons.saga.SagaCodes;
import com.ikite.seruvenn.SagaOrchestrator.exception.CommandGenerationException;
import com.ikite.seruvenn.SagaOrchestrator.exception.SagaDefinitionException;
import com.ikite.seruvenn.SagaOrchestrator.service.impl.SagaOrchestratorService;

@Service
public class DenemeService {

	@Autowired
	SagaOrchestratorService sagaOrchestratorService;
	
	public String callSampleSaga() {
		
		try {
			sagaOrchestratorService.startSaga(SagaCodes.SAMPLE_SAGA_CODE, new Foo());
		
		} catch (SagaDefinitionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} catch (CommandGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "deneme success";
		
	}
	
}
