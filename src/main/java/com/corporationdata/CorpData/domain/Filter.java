package com.corporationdata.CorpData.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Filter implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String email;
	
	@ManyToMany
	@JoinTable(name = "FILTER_CNAE",
	joinColumns = @JoinColumn(name = "filter_id"),
	inverseJoinColumns = @JoinColumn(name = "cnae_id")
	)
	private List<Cnae> cnaes  = new ArrayList<>();
	@ManyToMany
	@JoinTable(name = "FILTER_STATE",
	joinColumns = @JoinColumn(name = "filter_id"),
	inverseJoinColumns = @JoinColumn(name = "state_id")
	)
	private List<State> states = new ArrayList<>();
	@ManyToMany
	@JoinTable(name = "FILTER_CITY",
	joinColumns = @JoinColumn(name = "filter_id"),
	inverseJoinColumns = @JoinColumn(name = "city_id")
	)
	private List<City> cities = new ArrayList<>();
	private Boolean thereIsEmail;
	private Boolean thereIsAddress;
	private Boolean thereIsTelephone;
	@ManyToOne
	@JoinColumn(name="plan_id")
	private Plan plan;
	
	
	public Filter() {}

	public Filter(String email, Boolean thereIsEmail, Boolean thereIsAddress, Boolean thereIsTelephone, Plan plan) {
		super();
		this.email = email;
		this.thereIsEmail = thereIsEmail;
		this.thereIsAddress = thereIsAddress;
		this.thereIsTelephone = thereIsTelephone;
		this.plan = plan;
		
	}

	public List<Cnae> getCnaes() {
		return cnaes;
	}

	public void setCnaes(List<Cnae> cnaes) {
		this.cnaes = cnaes;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Boolean getThereIsEmail() {
		return thereIsEmail;
	}

	public void setThereIsEmail(Boolean thereIsEmail) {
		this.thereIsEmail = thereIsEmail;
	}

	public Boolean getThereIsAddress() {
		return thereIsAddress;
	}

	public void setThereIsAddress(Boolean thereIsAddress) {
		this.thereIsAddress = thereIsAddress;
	}

	public Boolean getThereIsTelephone() {
		return thereIsTelephone;
	}

	public void setThereIsTelephone(Boolean thereIsTelephone) {
		this.thereIsTelephone = thereIsTelephone;
	}

	public Plan getPlans() {
		return plan;
	}

	public void setPlans(Plan plan) {
		this.plan = plan;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
