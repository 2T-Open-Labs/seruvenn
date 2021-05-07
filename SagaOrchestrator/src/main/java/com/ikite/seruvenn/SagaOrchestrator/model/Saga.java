package com.ikite.seruvenn.SagaOrchestrator.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * 
 * Saga tanımının yapıldığı ana sınıftır. Kod alanı saga bazında unique'tir.
 * Örneğin bir Shopping Saga ile Para Yatırma Saga'sı farklı kodlar alacaktır
 * 
 * @author 197877
 *
 */
@Entity
@Table(name = "SAGA") 
public class Saga implements Serializable {
	
	//TODO Redis e dönüştürülebilir

	private static final long serialVersionUID = 1106054706378558789L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "KOD", unique = true)
	private String kod;
	
	@Column(name = "AD", unique = true)
	private String ad;

	@Column(name = "OLUSTURMA_TARIHI")
	private Date olusturmaTarihi;

	@Column(name = "STATUS")
	private Boolean status;
	//FIXME boolean mysql de olmuyor...
	
	@OneToMany(mappedBy = "saga", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("id")
	private Set<SagaState> stateList = new HashSet<SagaState>();
	

	
	public Saga() {
		super();
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public Date getOlusturmaTarihi() {
		return olusturmaTarihi;
	}

	public void setOlusturmatarihi(Date olusturmaTarihi) {
		this.olusturmaTarihi = olusturmaTarihi;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<SagaState> getStateList() {
		return stateList;
	}

	public void setStateList(Set<SagaState> stateList) {
		this.stateList = stateList;
	}


	public String getAd() {
		return ad;
	}


	public void setAd(String ad) {
		this.ad = ad;
	}
	
}
