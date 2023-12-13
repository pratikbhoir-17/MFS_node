package com.mfs.node.dao.dto;

public class NodeConfigRequestDto {

	private String method;
	private String name;
	private String partnerCode;
	private String currencyCode;
	private String operator;
	private String countryCode;
	private double minPerTxLimit;
	private double maxPerTxLimit;
	private String fileName;
	private String filePath;
	private String jarParams;
	private String prefix;
	private String curbiCode;
	private String reference;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public double getMinPerTxLimit() {
		return minPerTxLimit;
	}

	public void setMinPerTxLimit(double minPerTxLimit) {
		this.minPerTxLimit = minPerTxLimit;
	}

	public double getMaxPerTxLimit() {
		return maxPerTxLimit;
	}

	public void setMaxPerTxLimit(double maxPerTxLimit) {
		this.maxPerTxLimit = maxPerTxLimit;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getJarParams() {
		return jarParams;
	}

	public void setJarParams(String jarParams) {
		this.jarParams = jarParams;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getCurbiCode() {
		return curbiCode;
	}

	public void setCurbiCode(String curbiCode) {
		this.curbiCode = curbiCode;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Override
	public String toString() {
		return "NodeConfigRequestDto [method=" + method + ", name=" + name + ", partnerCode=" + partnerCode
				+ ", currencyCode=" + currencyCode + ", operator=" + operator + ", countryCode=" + countryCode
				+ ", minPerTxLimit=" + minPerTxLimit + ", maxPerTxLimit=" + maxPerTxLimit + ", fileName=" + fileName
				+ ", filePath=" + filePath + ", jarParams=" + jarParams + ", prefix=" + prefix + ", curbiCode="
				+ curbiCode + ", reference=" + reference + "]";
	}

}
