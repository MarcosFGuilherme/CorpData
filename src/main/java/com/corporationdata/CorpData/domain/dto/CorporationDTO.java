package com.corporationdata.CorpData.domain.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Id;

import com.corporationdata.CorpData.domain.Corporation;

public class CorporationDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cnpj;
	private String razao_social;
	private String nome_fantasia;
	private String situacao;
	private Date data_situacao;
	private Date data_inicio_ativ;
	private Integer cnae_fiscal;
	private String descricao_cnae;
	private Integer cod_nat_juridica;
	private String descricao_natureza_juridica;
	private String porte;
	private Double capital_social;
	private String opc_simples;
	private String opc_mei;
	private String tipo_logradouro;
	private String logradouro;
	private String bairro;
	private String cep;
	private String uf;
	private String municipio;
	private String numero;
	private String complemento;
	private String ddd_1;
	private String telefone_1;
	private String ddd_2;
	private String telefone_2;
	private String email;

	public CorporationDTO() {
	}

	public CorporationDTO(Corporation obj) {
		super();
		cnpj = obj.getDocument();
		razao_social = obj.getCompanyName();
		nome_fantasia = obj.getFantasyName();
		situacao = obj.getStatus().getDescription();
		data_situacao = obj.getStatusDate();
		data_inicio_ativ = obj.getActiveStartDate();
		cnae_fiscal = obj.getFiscalCnae().getId();
		descricao_cnae = obj.getFiscalCnae().getName();
		cod_nat_juridica = obj.getLegalNature().getId();
		descricao_natureza_juridica = obj.getLegalNature().getName();
		porte = obj.getSizeCompany().getDescription();
		capital_social = obj.getShareCapital();
		opc_simples = obj.getSimpleOption().getDescription();
		opc_mei = obj.getMeiOption().getDescription();
		tipo_logradouro = obj.getTypeStreet();
		logradouro = obj.getAddress();
		bairro = obj.getNeighborhood();
		cep = obj.getZipCode();
		uf = obj.getCity().getState().getUf();
		municipio = obj.getCity().getName();
		numero = obj.getNumber();
		complemento = obj.getComplement();
		Set<String> phones = obj.getPhones();
		Integer n = 0;
		for (Iterator<String> iter = phones.iterator(); iter.hasNext();) {
			String ph = iter.next();
			if (n==0) {
				ddd_1 = ph.substring(0, ph.lastIndexOf("-")-1);
				telefone_1 = ph.substring(ph.lastIndexOf("-")+1);
			}
			else {
				ddd_2 = ph.substring(0, ph.lastIndexOf("-")-1);
				telefone_2 = ph.substring(ph.lastIndexOf("-")+1);
			}
			n++;
		}
		email = obj.getEmail();
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getRazao_social() {
		return razao_social;
	}

	public String getNome_fantasia() {
		return nome_fantasia;
	}

	public String getSituacao() {
		return situacao;
	}

	public Date getData_situacao() {
		return data_situacao;
	}

	public Date getData_inicio_ativ() {
		return data_inicio_ativ;
	}

	public Integer getCnae_fiscal() {
		return cnae_fiscal;
	}

	public String getDescricao_cnae() {
		return descricao_cnae;
	}

	public Integer getCod_nat_juridica() {
		return cod_nat_juridica;
	}

	public String getDescricao_natureza_juridica() {
		return descricao_natureza_juridica;
	}

	public String getPorte() {
		return porte;
	}

	public Double getCapital_social() {
		return capital_social;
	}

	public String getOpc_simples() {
		return opc_simples;
	}

	public String getOpc_mei() {
		return opc_mei;
	}

	public String getTipo_logradouro() {
		return tipo_logradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCep() {
		return cep;
	}

	public String getUf() {
		return uf;
	}

	public String getMunicipio() {
		return municipio;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getDdd_1() {
		return ddd_1;
	}

	public String getTelefone_1() {
		return telefone_1;
	}

	public String getDdd_2() {
		return ddd_2;
	}

	public String getTelefone_2() {
		return telefone_2;
	}

	public String getEmail() {
		return email;
	}
	
	
}
