package com.ltifinance.loanapp.loanservice.constant;

public enum EnumData {

	PENDING("P"), APPROVED("A"), DISBURSED("D"),REJECTED("R");

	private final String value;

	EnumData(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static EnumData fromValue(String value) {
		for (EnumData data : EnumData.values()) {
			if (data.getValue().equals(value)) {
				return data;
			}
		}
		throw new IllegalArgumentException("Invalid Status Code");
	}
}
