package com.corporationdata.CorpData.domain.dto;

import java.io.Serializable;

import javax.persistence.Id;

import com.corporationdata.CorpData.domain.Plan;

public class BudgetDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String plano;
	private Integer maximoEmpresas;
	private Double valorUnitario;
	private Double valorMaximo;
	private Integer quantidade;
	private Double valorTotal;
	
	public BudgetDTO() {}
	
	public BudgetDTO(Plan obj, Integer quantidade) {
		id = obj.getId();
		plano = obj.getName();
		maximoEmpresas = obj.getTotalCompanies();
		valorUnitario = obj.getUnitPrice();
		valorMaximo = obj.getTotalPriceMax();
		this.quantidade = quantidade;
		valorTotal = obj.getTotalPrice(quantidade);
	}

	public Integer getId() {
		return id;
	}

	public String getPlano() {
		return plano;
	}

	public Integer getMaximoEmpresas() {
		return maximoEmpresas;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public Double getValorMaximo() {
		return valorMaximo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	
	
	
}
