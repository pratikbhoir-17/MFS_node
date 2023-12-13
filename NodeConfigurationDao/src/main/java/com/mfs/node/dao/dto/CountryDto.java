package com.mfs.node.dao.dto;

public class CountryDto {
	private String countryCode;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "CountryDto [countryCode=" + countryCode + "]";
	}

}
