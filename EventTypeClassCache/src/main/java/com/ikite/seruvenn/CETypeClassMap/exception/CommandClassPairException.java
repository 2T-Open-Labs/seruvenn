package com.ikite.seruvenn.CETypeClassMap.exception;

public class CommandClassPairException extends Exception {
	
	private static final long serialVersionUID = 2066495300652495934L;
	
	private String message;

	public CommandClassPairException(String message) {
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
