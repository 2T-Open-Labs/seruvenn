package com.ikite.seruvenn.CETypeClassMap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "COMMAND_TYPE_CLASS_NAME") 
public class CommandTypeClassName{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "COMMAND_TYPE")
	private String commandType;
	
	@Column(name = "CLASS_NAME")
	private String className;


}
