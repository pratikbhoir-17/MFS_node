package com.mfs.node.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cashpickup_partner")
public class CashPickupPartner {

	@Id
	@Column(name = "sp_code", length = 70, nullable = false)
	private String spCode;

	@Column(name = "reference", length = 70)
	private String reference;

	public String getSpCode() {
		return spCode;
	}

	public void setSpCode(String spCode) {
		this.spCode = spCode;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Override
	public String toString() {
		return "CashPickupPartner [spCode=" + spCode + ", reference=" + reference + "]";
	}

}
