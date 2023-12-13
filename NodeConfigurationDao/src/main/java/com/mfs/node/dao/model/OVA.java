package com.mfs.node.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ova")
public class OVA {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "SP_Code")
	private String SPCode;

	@Column(name = "RP_Code")
	private String RPCode;

	@Column(name = "ova")
	private String ova;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSPCode() {
		return SPCode;
	}

	public void setSPCode(String sPCode) {
		SPCode = sPCode;
	}

	public String getRPCode() {
		return RPCode;
	}

	public void setRPCode(String rPCode) {
		RPCode = rPCode;
	}

	public String getOva() {
		return ova;
	}

	public void setOva(String ova) {
		this.ova = ova;
	}

	@Override
	public String toString() {
		return "OVA [id=" + id + ", SPCode=" + SPCode + ", RPCode=" + RPCode + ", ova=" + ova + "]";
	}

}
