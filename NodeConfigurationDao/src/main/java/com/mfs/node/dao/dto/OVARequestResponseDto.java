package com.mfs.node.dao.dto;

public class OVARequestResponseDto {
	private String sendingPartner;
	private String receivingPartner;
	private String ova;

	public String getSendingPartner() {
		return sendingPartner;
	}

	public void setSendingPartner(String sendingPartner) {
		this.sendingPartner = sendingPartner;
	}

	public String getReceivingPartner() {
		return receivingPartner;
	}

	public void setReceivingPartner(String receivingPartner) {
		this.receivingPartner = receivingPartner;
	}

	public String getOva() {
		return ova;
	}

	public void setOva(String ova) {
		this.ova = ova;
	}

	@Override
	public String toString() {
		return "OVARequestDto [sendingPartner=" + sendingPartner + ", receivingPartner=" + receivingPartner + ", ova="
				+ ova + "]";
	}
}
