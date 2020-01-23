package com.corporationdata.CorpData.domain.enums;

public enum Status {
	OTHES(0, "BAIXADOS,INAMITOS, ETC"),
	NULL(1,"NULA"),
	ACTIVE(2,"ATIVA"),
	SUSPENDED(3, "SUSPENSA"),
	INACTIVE(4, "INAPTA");
	
	
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
	
	public static Status toEnum (Integer code) {
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
