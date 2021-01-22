package com.ikite.seruvenn.SagaOrchestrator.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * Saga'ya ait akışların olduğu sınıftır. Sıralı değil stateless tüm branch ler bu tipte tanımlanır. 
 * SagaID ve CommandType üzerinde bir unique constraint bulunmaktadır.
 * Bunun sebebi iç içe geçen akışları, alt Saga'lar olarak tanımlamaya zorlamak maksadıyla
 * bir Saga içinde aynı Command'in tekrar çağrılmamasını sağlamaktır.
 * 
 * @author Oğuzhan Ceylan
 * 
 */

@Entity
@Table(name = "SAGA_STATE", uniqueConstraints={@UniqueConstraint(columnNames = {"SAGA_ID", "COMMAND_TYPE"})}) 
public class SagaState implements Serializable {

	private static final long serialVersionUID = 4418235938076387840L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "EVENT_TYPE")
	private String eventType;
	
	@Column(name = "COMMAND_TYPE")
	private String commandType;
	
	/**
	 * 0 veya 1 alacak, mysql veya redis ne olacaksa ona göre burası setlenebilir
	 * 1 olduğunda rollback metodu olduğu anlaşılır
	 */
	@Column(name = "REVERSE_COMMAND")
	private Integer reverseCommand;
	
	@ManyToOne
    @JoinColumn(name="SAGA_ID")
	private Saga saga;
	
	
	public SagaState() {
		super();
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

	public String getCommandType() {
		return commandType;
	}

	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}

	public Saga getSaga() {
		return saga;
	}

	public void setSaga(Saga saga) {
		this.saga = saga;
	}


	public Integer getReverseCommand() {
		return reverseCommand;
	}


	public void setReverseCommand(Integer reverseCommand) {
		this.reverseCommand = reverseCommand;
	}

	
	
	
	
}
