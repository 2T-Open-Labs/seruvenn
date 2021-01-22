package com.ikite.seruvenn.Commons.kafka;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public abstract class KafkaMessageInnerEntity implements Serializable{

	
}
