package com.corporationdata.CorpData.domain.enums;

public enum Simple {
	YES(1,"Sim"),
	NO(2, "Nao"),
	EXCLUDED(3, "Excluido"),
	UNINFORMED(9, "Nao Informado");
	
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
