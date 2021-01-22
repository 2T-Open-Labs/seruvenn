package com.ikite.seruvenn.CETypeClassMap.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COMMAND_TYPE_CLASS_NAME") 
public class CommandTypeClassName implements Serializable{


	private static final long serialVersionUID = 5947509727850059259L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "COMMAND_TYPE")
	private String commandType;
	
	@Column(name = "CLASS_NAME")
	private String className;

	public CommandTypeClassName() {
		super();
	}

	public CommandTypeClassName(String commandType, String className) {
		super();
		this.commandType = commandType;
		this.className = className;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommandType() {
		return commandType;
	}

	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
