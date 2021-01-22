package com.ikite.seruvenn.SagaOrchestrator.exception;

public class SagaDefinitionException extends Exception {

	private static final long serialVersionUID = 1387179968743521023L;
	
	private String message;

	public SagaDefinitionException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return message;
	}

}
