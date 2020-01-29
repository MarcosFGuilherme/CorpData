package com.corporationdata.CorpData.domain.dto;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
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
	private Date abertura;
	private Integer cnae;
	private String ramo_de_atividade;
	private Integer codigo_natureza_juridica;
	private String natureza_juridica;
	private String porte;
	private Double capital_social_r$;
	private String simples_nacional;
	private String mei;
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
		abertura = obj.getActiveStartDate();
		cnae = obj.getFiscalCnae().getId();
		ramo_de_atividade = obj.getFiscalCnae().getName();
		codigo_natureza_juridica = obj.getLegalNature().getId();
		natureza_juridica = obj.getLegalNature().getName();
		porte = obj.getSizeCompany().getDescription();
		capital_social_r$ = obj.getShareCapital();
		simples_nacional = obj.getSimpleOption().getDescription();
		mei = obj.getMeiOption().getDescription();
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
				ddd_1 = ph.substring(0, ph.lastIndexOf("-")).replace(".0", "");
				telefone_1 = ph.substring(ph.lastIndexOf("-")+1);
			}
			else {
				ddd_2 = ph.substring(0, ph.lastIndexOf("-")).replace(".0", "");
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

	public Date getAbertura() {
		return abertura;
	}

	public Integer getCnae() {
		return cnae;
	}

	public String getRamo_de_atividade() {
		return ramo_de_atividade;
	}

	public Integer getCodigo_natureza_juridica() {
		return codigo_natureza_juridica;
	}

	public String getNatureza_juridica() {
		return natureza_juridica;
	}

	public String getPorte() {
		return porte;
	}

	public Double getCapital_social_r$() {
		return capital_social_r$;
	}

	public String getSimples_nacional() {
		return simples_nacional;
	}

	public String getMei() {
		return mei;
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

	@Override
	public String toString() {
		return 	"" 
				+ cnpj + "#" 
				+ razao_social + "#" 
				+ nome_fantasia + "#"
				+ situacao  + "#"
				+ data_situacao  + "#"
				+ abertura  + "#"
				+ cnae  + "#"
				+ ramo_de_atividade + "#" 
				+ codigo_natureza_juridica + "#" 
				+ natureza_juridica  + "#"
				+ porte + "#"
				+ capital_social_r$ + "#" 
				+ simples_nacional  + "#"
				+ mei + "#"
				+ tipo_logradouro  + "#"
				+ logradouro  + "#"
				+ bairro + "#"
				+ cep  + "#"
				+ uf  + "#"
				+ municipio + "#" 
				+ numero  + "#"
				+ complemento  + "#"
				+ ddd_1  + "#"
				+ telefone_1  + "#"
				+ ddd_2  + "#"
				+ telefone_2  + "#"
				+ email;  
	}
	
	public String toFields() {
		String str="";
		for(Field f : getClass().getDeclaredFields())
		{
			if (!f.getName().equals("serialVersionUID")) {
				str = str + f.getName().toUpperCase().replace("_", " ") + "#";
			}
		}
		return str.substring(0, str.length()-1);
	}
}
