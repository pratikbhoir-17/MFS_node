package com.mfs.node.dao.dto;

import java.util.List;

import com.mfs.node.dao.model.Countries;
import com.mfs.node.dao.model.Currencies;
import com.mfs.node.dao.model.MethodType;

public class VerifyMfsaResponseDto {
	List<PartnerConfigResponseDto> partnerList;
	List<Countries> countryList;
	List<Currencies> currencyList;
	List<MethodType> methodTypeList;

	public List<PartnerConfigResponseDto> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(List<PartnerConfigResponseDto> partnerList) {
		this.partnerList = partnerList;
	}

	public List<Countries> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Countries> countryList) {
		this.countryList = countryList;
	}

	public List<Currencies> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<Currencies> currencyList) {
		this.currencyList = currencyList;
	}

	public List<MethodType> getMethodTypeList() {
		return methodTypeList;
	}

	public void setMethodTypeList(List<MethodType> methodTypeList) {
		this.methodTypeList = methodTypeList;
	}

	@Override
	public String toString() {
		return "VerifyOtpResponseDto [partnerList=" + partnerList + ", countryList=" + countryList + ", currencyList="
				+ currencyList + ", methodTypeList=" + methodTypeList + "]";
	}

}
