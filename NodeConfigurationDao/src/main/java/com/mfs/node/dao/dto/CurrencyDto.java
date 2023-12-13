package com.mfs.node.dao.dto;

public class CurrencyDto {
	private String currencySymbol;

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	@Override
	public String toString() {
		return "CurrencyDto [currencySymbol=" + currencySymbol + "]";
	}

}
