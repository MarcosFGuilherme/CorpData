package com.corporationdata.CorpData.domain.enums;

public enum Status {
	ACTIVE(1,"Ativa"),
	INACTIVE(2, "Inativa"),
	UNINFORMED(9, "Nao Informada");
	
	private Integer code;
	private String description;
	
	private Status(Integer code, String description) {
		this.code = code;
		this.description = description;
	}
	public int getCode() {
		return code;
	}
	public String getDescription() {
		return description;
	}
	
	public Status toEnum (Integer code) {
		if (code == null) {
			return null;
		}
		for (Status x : Status.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid Code: " + code);
	}
}
