package com.corporationdata.CorpData.domain.enums;

public enum Size {
	MICRO(1,"MICRO EMPRESA"),
	SMALL(3, "EMPRESA DE PEQUENO PORTE"),
	OHTERS(5, "DEMAIS"),
	UNINFORMED(0, "NAO INFORMADO");
	
	private Integer code;
	private String description;
	
	private Size(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}

	public static Size toEnum(Integer code) {
		if (code == null) {
			return null;
		}
		for (Size x : Size.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid Code: " + code);
	}
	
	
}
