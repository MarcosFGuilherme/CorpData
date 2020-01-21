package com.corporationdata.CorpData.domain.enums;

public enum Size {
	SMALL(1,"Pequena"),
	AVERAGE(2, "Media"),
	BIG(3, "Grande"),
	UNINFORMED(9, "Nao Informada");
	
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
