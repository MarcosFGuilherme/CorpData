package com.corporationdata.CorpData.domain.dto;

import java.io.Serializable;

import javax.persistence.Id;

import com.corporationdata.CorpData.domain.Plan;

public class PlanDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String name;
	private Integer totalCompanies;
	private Double unitPrice;

	public PlanDTO () {}
	
	public PlanDTO (Plan obj) {
		super();
		id = obj.getId();
		name =  obj.getName();
		totalCompanies = obj.getTotalCompanies();
		unitPrice = obj.getUnitPrice();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalCompanies() {
		return totalCompanies;
	}

	public void setTotalCompanies(Integer totalCompanies) {
		this.totalCompanies = totalCompanies;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	
}
