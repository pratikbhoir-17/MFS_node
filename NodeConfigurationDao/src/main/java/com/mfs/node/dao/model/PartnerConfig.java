package com.mfs.node.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "partner_configs")
public class PartnerConfig {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "partner_id")
	private int partnerId;

	@Column(name = "partner_code", length = 255)
	private String partnerCode;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "currency_code", length = 3)
	private String currencyCode;

	@Column(name = "operator", length = 20)
	private String operator;

	@Column(name = "country_code", length = 3)
	private String countryCode;

	@Column(name = "min_per_tx_limit")
	private double minPerTxLimit;

	@Column(name = "max_per_tx_limit")
	private double maxPerTxLimit;

	@Column(name = "jar_file_path", columnDefinition = "LONGTEXT")
	private String jarFilePath;

	@Column(name = "jar_file_name")
	private String jarFileName;

	@Column(name = "jar_params", columnDefinition = "LONGTEXT")
	private String jarParams;

	@Column(name = "method")
	private String method;

	@Column(name = "prefix", length = 50)
	private String prefix;

	@Column(name = "curbi_code", length = 20)
	private String curbiCode;

	@Column(name = "reference", length = 70)
	private String reference;

	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getJarFilePath() {
		return jarFilePath;
	}

	public void setJarFilePath(String jarFilePath) {
		this.jarFilePath = jarFilePath;
	}

	public String getJarFileName() {
		return jarFileName;
	}

	public void setJarFileName(String jarFileName) {
		this.jarFileName = jarFileName;
	}

	public String getJarParams() {
		return jarParams;
	}

	public void setJarParams(String jarParams) {
		this.jarParams = jarParams;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
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
		return "PartnerConfig [partnerId=" + partnerId + ", partnerCode=" + partnerCode + ", name=" + name
				+ ", currencyCode=" + currencyCode + ", operator=" + operator + ", countryCode=" + countryCode
				+ ", minPerTxLimit=" + minPerTxLimit + ", maxPerTxLimit=" + maxPerTxLimit + ", jarFilePath="
				+ jarFilePath + ", jarFileName=" + jarFileName + ", jarParams=" + jarParams + ", method=" + method
				+ ", prefix=" + prefix + ", curbiCode=" + curbiCode + ", reference=" + reference + "]";
	}

}
