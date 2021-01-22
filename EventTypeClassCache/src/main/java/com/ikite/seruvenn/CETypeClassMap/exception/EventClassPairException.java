package com.ikite.seruvenn.CETypeClassMap.exception;

public class EventClassPairException extends Exception {
	
	private static final long serialVersionUID = 3442900396388734254L;
	
	private String message;

	public EventClassPairException(String message) {
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
