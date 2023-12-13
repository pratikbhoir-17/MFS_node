package com.mfs.node.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
public class Countries {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "country_name",nullable = false)
	private String countryName;

	@Column(name = "country_code",nullable = false)
	private String countryCode;

	@Column(name = "numeric_code",nullable = false)
	private String numericCode;

	@Column(name = "phone_code",nullable = false)
	private String phoneCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getNumericCode() {
		return numericCode;
	}

	public void setNumericCode(String numericCode) {
		this.numericCode = numericCode;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	@Override
	public String toString() {
		return "Countries [id=" + id + ", countryName=" + countryName + ", countryCode=" + countryCode
				+ ", numericCode=" + numericCode + ", phoneCode=" + phoneCode + "]";
	}

}
