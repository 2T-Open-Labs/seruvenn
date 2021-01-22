package com.ikite.seruvenn.SagaOrchestrator.exception;

public class CommandGenerationException extends Exception {
	
	private static final long serialVersionUID = 807079641210439302L;
	
	private String message;

	public CommandGenerationException(String message) {
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
