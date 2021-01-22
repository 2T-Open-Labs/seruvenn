package com.ikite.seruvenn.Commons.carriers;

import com.ikite.seruvenn.Commons.kafka.KafkaMessageInnerEntity;
import com.ikite.seruvenn.Commons.message.Message;

@SuppressWarnings("rawtypes")
public class Joined extends KafkaMessageInnerEntity{

	private static final long serialVersionUID = -9149088279640749291L;
	
	private Message primaryEvent;
	private Message joiningEvent;
	
	public Joined(Message primaryEvent, Message joiningEvent) {
		super();
		this.primaryEvent = primaryEvent;
		this.joiningEvent = joiningEvent;
	}

	public Message getPrimaryEvent() {
		return primaryEvent;
	}

	public void setPrimaryEvent(Message primaryEvent) {
		this.primaryEvent = primaryEvent;
	}

	public Message getJoiningEvent() {
		return joiningEvent;
	}

	public void setJoiningEvent(Message joiningEvent) {
		this.joiningEvent = joiningEvent;
	}
	
	
}
