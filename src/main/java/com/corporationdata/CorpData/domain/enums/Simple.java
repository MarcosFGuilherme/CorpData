package com.corporationdata.CorpData.domain.enums;

public enum Simple {
	YES(5,"OPTANTE PELO SIMPLES"),
	NO(0, "NAO OPTANTE"),
	EXCLUDED(6, "EXCLUIDO DO SIMPLES");
	
	private Integer code;
	private String description;
	
	private Simple(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static Simple toEnum(Integer code) {
		if (code == null) {
			return null;
		}
		for (Simple x : Simple.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid Code: " + code);
	}
}
