package com.ikite.seruvenn.SagaOrchestrator.common;

import com.ikite.seruvenn.Commons.kafka.KafkaMessageInnerEntity;

public class Body extends KafkaMessageInnerEntity{

	private static final long serialVersionUID = -529796512573284851L;
	
	private int o2;
	private int co2;
	private int atik;
	
	public Body() {
	}

	public Body(int o2, int co2, int atik) {
		this.o2 = o2;
		this.co2 = co2;
		this.atik = atik;
	}

	public int getO2() {
		return o2;
	}

	public void setO2(int o2) {
		this.o2 = o2;
	}

	public int getCo2() {
		return co2;
	}

	public void setCo2(int co2) {
		this.co2 = co2;
	}

	public int getAtik() {
		return atik;
	}

	public void setAtik(int atik) {
		this.atik = atik;
	}

}
