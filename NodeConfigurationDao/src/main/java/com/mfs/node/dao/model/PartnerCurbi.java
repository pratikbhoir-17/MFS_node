package com.mfs.node.dao.model;

public class PartnerCurbi {
	private String corporate_code;
	private String curbi_trxnid;
	private String currency_code;
	private String country_code;

	public String getCorporate_code() {
		return corporate_code;
	}

	public void setCorporate_code(String corporate_code) {
		this.corporate_code = corporate_code;
	}

	public String getCurbi_trxnid() {
		return curbi_trxnid;
	}

	public void setCurbi_trxnid(String curbi_trxnid) {
		this.curbi_trxnid = curbi_trxnid;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	@Override
	public String toString() {
		return "PartnerCurbi [corporate_code=" + corporate_code + ", curbi_trxnid=" + curbi_trxnid + ", currency_code="
				+ currency_code + ", country_code=" + country_code + "]";
	}

}
