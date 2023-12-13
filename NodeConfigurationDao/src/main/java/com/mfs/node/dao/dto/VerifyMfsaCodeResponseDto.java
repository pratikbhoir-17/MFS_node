package com.mfs.node.dao.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class VerifyMfsaCodeResponseDto {
	@JsonInclude(Include.NON_NULL)
	private List<PartnerConfigResponseDto> partnerList;
	@JsonInclude(Include.NON_NULL)
	private List<CountryDto> countryList;
	@JsonInclude(Include.NON_NULL)
	private List<CurrencyDto> currencyList;
	@JsonInclude(Include.NON_NULL)
	private List<MethodDto> methodTypeList;
	@JsonInclude(Include.NON_NULL)
	private List<JarParamDto> paramList;
	@JsonInclude(Include.NON_NULL)
	private ResponseStatus status;

	public List<PartnerConfigResponseDto> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(List<PartnerConfigResponseDto> partnerList) {
		this.partnerList = partnerList;
	}

	public List<CountryDto> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<CountryDto> countryList) {
		this.countryList = countryList;
	}

	public List<CurrencyDto> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<CurrencyDto> currencyList) {
		this.currencyList = currencyList;
	}

	public List<MethodDto> getMethodTypeList() {
		return methodTypeList;
	}

	public void setMethodTypeList(List<MethodDto> methodTypeList) {
		this.methodTypeList = methodTypeList;
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public List<JarParamDto> getParamList() {
		return paramList;
	}

	public void setParamList(List<JarParamDto> paramList) {
		this.paramList = paramList;
	}

	@Override
	public String toString() {
		return "VerifyMfsaCodeResponseDto [partnerList=" + partnerList + ", countryList=" + countryList
				+ ", currencyList=" + currencyList + ", methodTypeList=" + methodTypeList + ", status=" + status
				+ ", paramList=" + paramList + "]";
	}

}
