package com.mfs.node.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Currencies")
public class Currencies {
	@Id
	@GeneratedValue
	@Column(name = "currency_code", length = 4)
	private int currencyCode;

	@Column(name = "symbol", length = 10, nullable = false)
	private String symbol;

	@Column(name = "description", length = 100, nullable = false)
	private String description;

	public int getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(int currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Currencies [currencyCode=" + currencyCode + ", symbol=" + symbol + ", description=" + description + "]";
	}

}
