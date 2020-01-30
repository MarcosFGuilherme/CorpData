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
	private List<Integer> cnaes = new ArrayList<>();
	private List<Integer> states = new ArrayList<>();
	private List<Integer> cities = new ArrayList<>();
	private Boolean thereIsEmail;
	private Boolean thereIsAddress;
	private Boolean thereIsTelephone;
	private Integer plan;
	
	public FilterDTO() {}

	public FilterDTO(Filter obj) {
		super();
		email = obj.getEmail();
		cnaes = toCnaesId(obj.getCnaes());
		states = toStatesId(obj.getStates());
		cities = toCitiesId(obj.getCities());
		thereIsEmail =obj.getThereIsEmail();
		thereIsAddress = obj.getThereIsAddress();
		thereIsTelephone = obj.getThereIsTelephone();
		plan = obj.getPlans().getId();
	}

	public String getEmail() {
		return email;
	}

	private List<Integer> toCnaesId(List<Cnae> cnaes) {
		List<Integer> list = new ArrayList<>();
		for(Cnae c: cnaes) {
			list.add(c.getId());
		}
		return list;
	}

	private List<Integer> toStatesId(List<State> states) {
		List<Integer> list = new ArrayList<>();
		for(State c: states) {
			list.add(c.getId());
		}
		return list;
	}

	private List<Integer> toCitiesId(List<City> cities) {
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
		return plan;
	}

	public List<Integer> getCnaes() {
		return cnaes;
	}

	public List<Integer> getStates() {
		return states;
	}

	public List<Integer> getCities() {
		return cities;
	}
	
	
	
}
