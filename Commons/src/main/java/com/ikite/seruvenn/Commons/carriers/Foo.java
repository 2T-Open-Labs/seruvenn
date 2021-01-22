package com.ikite.seruvenn.Commons.carriers;

import com.ikite.seruvenn.Commons.kafka.KafkaMessageInnerEntity;

public class Foo extends KafkaMessageInnerEntity{

	private static final long serialVersionUID = -529796512573284851L;
	
	private String message;
	
	
	public Foo() {
	}

	public Foo(String message) {
		super();
		this.message = message;
	}
	

	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}



}
