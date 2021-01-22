package com.ikite.seruvenn.SagaOrchestrator.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ikite.seruvenn.SagaOrchestrator.model.Saga;
import com.ikite.seruvenn.SagaOrchestrator.model.SagaState;

@Component
public class SagaStateBean {
	
	//FIXME ileride Redis vb bir caching e döndürülmeli

	public List<Saga> runtimeSagaList = new ArrayList<Saga>();
	
	public List<String> getEventTypesFrom(Saga currentSaga) {
		
		List<String> result = new ArrayList<String>();
		
		for (Saga saga : this.runtimeSagaList) {
			if (saga.equals(currentSaga)) {
				for (SagaState state : saga.getStateList()) {
					result.add(state.getEventType());
				}
			}
		}
		return result;
		
	}
	
	/**
	 * 
	 * Saga içerisindeki forward yönlü (success path) state lerin listesini getirir.
	 * 
	 * @param sagaCode alanı hangi Saga'nın bellekten çekileceği parametresidir
	 * @param eventType hangi event karşılığında command çıkılacağı parametresidir
	 * @return CommandType alanı dönülür
	 */
	public List<String> getForwardCommandListFrom(String sagaCode, String eventType) {
		
		List<String> result = new ArrayList<>();
		
		for (Saga saga : this.runtimeSagaList) {
			if (saga.getKod().equals(sagaCode)) {
				for (SagaState sagaState : saga.getStateList()) {
					if (sagaState.getEventType() != null && sagaState.getEventType().equals(eventType) && sagaState.getReverseCommand() == 0) {
						result.add(sagaState.getCommandType());
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 
	 * Saga içerisindeki reverse yönlü (fail path) state lerin listesini getirir.
	 * 
	 * @param sagaCode alanı hangi Saga'nın bellekten çekileceği parametresidir
	 * @param eventType hangi event karşılığında command çıkılacağı parametresidir
	 * @return CommandType alanı dönülür
	 */
	public String getReverseCommandListFrom(String sagaCode, String eventType) {
		
		for (Saga saga : this.runtimeSagaList) {
			if (saga.getKod().equals(sagaCode)) {
				for (SagaState sagaState : saga.getStateList()) {
					if (sagaState.getEventType() != null && sagaState.getEventType().equals(eventType) && sagaState.getReverseCommand() == 1) {
						return sagaState.getCommandType();
					}
				}
			}
		}
		return null;  
		//FIXME buraya bi özel exception eklenebilir, 
		//		örn: SagaStateBeanException, açıklama reverse command bulunamadı
	}

	public List<Saga> getRuntimeSagaList() {
		return runtimeSagaList;
	}

	public void addToSagaList(Saga saga) {
		
		boolean found = false;
		for (Saga iterSaga : this.runtimeSagaList) {
			if (iterSaga.getKod().equals(saga.getKod())) {
				found = true;
			}
		}
		
		if (!found) {
			this.runtimeSagaList.add(saga);
		}
		
//		if (!this.runtimeSagaList.contains(saga)) {
//			this.runtimeSagaList.add(saga);
//		}
	}
	
}
