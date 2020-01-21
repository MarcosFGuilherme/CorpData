package com.corporationdata.CorpData.domain.enums;

public enum Mei {
	YES(1,"SIM"),
	NO(2, "NAO"),
	OTHERS(0, "BRANCOS,ETC");
	
	private Integer code;
	private String description;
	
	private Mei(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static Mei toEnum(Integer code) {
		if (code == null) {
			return null;
		}
		for (Mei x : Mei.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid Code: " + code);
	}
}
