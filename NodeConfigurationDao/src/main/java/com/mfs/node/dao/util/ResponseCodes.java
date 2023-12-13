package com.mfs.node.dao.util;
public enum ResponseCodes {
	
	S200("200" , "Success"),
	ER500("500", ""), 
	ER501("ER501", "Invalid UserName"),
	ER502("ER502", "Invalid Password"),
	ER503("ER503", "Invalid PartnerCode"),
	ER504("ER504", "Invalid Operator"),
	ER505("ER505", "Invalid CountryCode"),
	ER506("ER506", "Invalid Jar FilePath"),
	ER507("ER507", "Invalid Jar Name"),
	ER508("ER508", "Invalid JarParams"),
	ER509("ER509", "Invalid Method"),
	ER510("ER510", "Invalid Otp"),
	ER511("ER511", "Invalid MfsaCode"),
	ER512("ER512", "Invalid CurrencyCode"),
	ER513("ER513", "Invalid Login Details"),
	ER514("ER514", "Internal Server Error"),
	ER515("ER515", "email should not be blank or null"),
	ER516("ER516", "UserName should not be blank or null"),
	ER517("ER517", "This method for perticular partner is already config"),
	ER518("ER518", "sendingPartner should not be blank or null"),
	ER519("ER519", "receivingPartner should not be blank or null"),
	ER520("ER520", "ova should not be blank or null");
	
	private String code;
	private String message;
	

	private ResponseCodes(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
