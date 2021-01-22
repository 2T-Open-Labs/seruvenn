package com.ikite.seruvenn.Commons.message;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.ikite.seruvenn.Commons.kafka.KafkaMessageInnerEntity;

public class Message <T extends KafkaMessageInnerEntity> implements Serializable{

	private static final long serialVersionUID = 5615805441840178149L;
	
	private String id;
	private String transactionId;
	private String archetype;
	private String messageType;
	private Date creationDate;
	private String sagaCode;
	
	private T carrierEntity;
	
	public Message() {
		super();
	}
	
	public Message(String id, 
			 String transactionId, 
			 String archetype,
			 String messageType, 
			 Date creationDate, 
			 int version, 
			 String sagaCode,
			 T eventEntity) {
		super();
		this.id = id;
		this.transactionId = transactionId;
		this.archetype = archetype;
		this.messageType = messageType;
		this.creationDate = creationDate;
		this.sagaCode = sagaCode;
		this.carrierEntity = eventEntity;
	}

	public Message(String archetype, String messageType, int version, String sagaCode, T eventEntity) {
		super();
		this.id = UUID.randomUUID().toString();
		this.transactionId = UUID.randomUUID().toString();
		this.archetype = archetype;
		this.messageType = messageType;
		this.creationDate = new Date();
		this.sagaCode = sagaCode;
		this.carrierEntity = eventEntity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getSagaCode() {
		return sagaCode;
	}

	public void setSagaCode(String sagaCode) {
		this.sagaCode = sagaCode;
	}

	public String getArchetype() {
		return archetype;
	}

	public void setArchetype(String archetype) {
		this.archetype = archetype;
	}

	public T getCarrierEntity() {
		return carrierEntity;
	}

	public void setCarrierEntity(T carrierEntity) {
		this.carrierEntity = carrierEntity;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessageType() {
		return messageType;
	}

	
	
}
