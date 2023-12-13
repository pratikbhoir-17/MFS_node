package com.mfs.node.dao.dto;

public class Login {
	private String userName;
	private String password;
	private String otp;
	private String mfsaCode;

	public String getMfsaCode() {
		return mfsaCode;
	}

	public void setMfsaCode(String mfsaCode) {
		this.mfsaCode = mfsaCode;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Login [userName=" + userName + ", password=" + password + ", otp=" + otp + ", mfsaCode=" + mfsaCode
				+ "]";
	}

}
