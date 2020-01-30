package com.corporationdata.CorpData.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import com.corporationdata.CorpData.domain.City;
import com.corporationdata.CorpData.domain.Cnae;
import com.corporationdata.CorpData.domain.Filter;
import com.corporationdata.CorpData.domain.Plan;
import com.corporationdata.CorpData.domain.State;

public class FilterDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String email;
	private List<Cnae> cnaes = new ArrayList<>();
	private List<State> states = new ArrayList<>();
	private List<City> cities = new ArrayList<>();
	private Boolean thereIsEmail;
	private Boolean thereIsAddress;
	private Boolean thereIsTelephone;
	private Plan plan;
	
	public FilterDTO() {}

	public FilterDTO(Filter obj) {
		super();
		email = obj.getEmail();
		cnaes = obj.getCnaes();
		states = obj.getStates();
		cities = obj.getCities();
		thereIsEmail =obj.getThereIsEmail();
		thereIsAddress = obj.getThereIsAddress();
		thereIsTelephone = obj.getThereIsTelephone();
		plan = obj.getPlans();
	}

	public String getEmail() {
		return email;
	}

	public List<Integer> getCnaes() {
		List<Integer> list = new ArrayList<>();
		for(Cnae c: cnaes) {
			list.add(c.getId());
		}
		return list;
	}

	public List<Integer> getStates() {
		List<Integer> list = new ArrayList<>();
		for(State c: states) {
			list.add(c.getId());
		}
		return list;
	}

	public List<Integer> getCities() {
		List<Integer> list = new ArrayList<>();
		for(City c: cities) {
			list.add(c.getId());
		}
		return list;
	}

	public Boolean getThereIsEmail() {
		return thereIsEmail;
	}

	public Boolean getThereIsAddress() {
		return thereIsAddress;
	}

	public Boolean getThereIsTelephone() {
		return thereIsTelephone;
	}

	public Integer getPlan() {
		Integer id = plan.getId();
		return id;
	}
	
	
	
}
