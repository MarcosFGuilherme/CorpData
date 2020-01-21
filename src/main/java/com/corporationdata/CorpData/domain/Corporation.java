package com.corporationdata.CorpData.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.corporationdata.CorpData.domain.enums.Mei;
import com.corporationdata.CorpData.domain.enums.Simple;
import com.corporationdata.CorpData.domain.enums.Size;
import com.corporationdata.CorpData.domain.enums.Status;

@Entity
public class Corporation implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String document;
	private String companyName;
	private String fantasyName;
	private Status status;
	private Date statusDate;
	private Date activeStartDate;
	@ManyToOne
	@JoinColumn(name = "cnae_id")
	private Cnae fiscalCnae;
	@ManyToOne
	@JoinColumn(name = "legalnature_id")
	private LegalNature legalNature;
	private Size sizeCompany;
	private Double shareCapital;
	private Simple	simpleOption;
	private Mei	meiOption;
	private String typeStreet;
	private String address;
	private String neighborhood;
	private String	zipCode;
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	private String number;
	private String complement;
	private String email;
	@ElementCollection
	@CollectionTable(name = "PHONES")
	private Set<String> phones = new HashSet<>();
	
	public Corporation() {}

	public Corporation(String document, String companyName, String fantasyName, Status status, Date statusDate,
			Date activeStartDate, Cnae fiscalCnae, LegalNature legalNature, Size sizeCompany, Double shareCapital,
			Simple simpleOption, Mei meiOption, String typeStreet, String address, String neighborhood, String zipCode,
			City city, String number, String complement, String email) {
		super();
		this.document = document;
		this.companyName = companyName;
		this.fantasyName = fantasyName;
		this.status = status;
		this.statusDate = statusDate;
		this.activeStartDate = activeStartDate;
		this.fiscalCnae = fiscalCnae;
		this.legalNature = legalNature;
		this.sizeCompany = sizeCompany;
		this.shareCapital = shareCapital;
		this.simpleOption = simpleOption;
		this.meiOption = meiOption;
		this.typeStreet = typeStreet;
		this.address = address;
		this.neighborhood = neighborhood;
		this.zipCode = zipCode;
		this.city = city;
		this.number = number;
		this.complement = complement;
		this.email = email;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getFantasyName() {
		return fantasyName;
	}

	public void setFantasyName(String fantasyName) {
		this.fantasyName = fantasyName;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public Date getActiveStartDate() {
		return activeStartDate;
	}

	public void setActiveStartDate(Date activeStartDate) {
		this.activeStartDate = activeStartDate;
	}

	public Cnae getFiscalCnae() {
		return fiscalCnae;
	}

	public void setFiscalCnae(Cnae fiscalCnae) {
		this.fiscalCnae = fiscalCnae;
	}

	public LegalNature getLegalNature() {
		return legalNature;
	}

	public void setLegalNature(LegalNature legalNature) {
		this.legalNature = legalNature;
	}

	public Size getSizeCompany() {
		return sizeCompany;
	}

	public void setSizeCompany(Size sizeCompany) {
		this.sizeCompany = sizeCompany;
	}

	public Double getShareCapital() {
		return shareCapital;
	}

	public void setShareCapital(Double shareCapital) {
		this.shareCapital = shareCapital;
	}

	public Simple getSimpleOption() {
		return simpleOption;
	}

	public void setSimpleOption(Simple simpleOption) {
		this.simpleOption = simpleOption;
	}

	public Mei getMeiOption() {
		return meiOption;
	}

	public void setMeiOption(Mei meiOption) {
		this.meiOption = meiOption;
	}

	public String getTypeStreet() {
		return typeStreet;
	}

	public void setTypeStreet(String typeStreet) {
		this.typeStreet = typeStreet;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public Set<String> getPhones() {
		return phones;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((document == null) ? 0 : document.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corporation other = (Corporation) obj;
		if (document == null) {
			if (other.document != null)
				return false;
		} else if (!document.equals(other.document))
			return false;
		return true;
	}
	
}
