package com.ikite.seruvenn.CETypeClassMap.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EVENT_TYPE_CLASS_NAME") 
public class EventTypeClassName implements Serializable{


	private static final long serialVersionUID = 5947509727850059259L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "EVENT_TYPE")
	private String eventType;
	
	@Column(name = "CLASS_NAME")
	private String className;

	public EventTypeClassName() {
		super();
	}

	public EventTypeClassName(String eventType, String className) {
		super();
		this.eventType = eventType;
		this.className = className;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
