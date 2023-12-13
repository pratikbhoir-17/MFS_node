package com.mfs.node.dao.dto;

public class DeleteDto {

	private String method;
	private String partnerCode;

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

	@Override
	public String toString() {
		return "DeleteDto [method=" + method + ", partnerCode=" + partnerCode + "]";
	}

}
