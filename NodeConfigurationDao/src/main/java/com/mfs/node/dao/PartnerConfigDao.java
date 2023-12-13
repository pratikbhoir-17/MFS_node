package com.mfs.node.dao;

import java.util.List;

import com.mfs.node.dao.model.Countries;
import com.mfs.node.dao.model.Currencies;
import com.mfs.node.dao.model.JarParameters;
import com.mfs.node.dao.model.MethodType;
import com.mfs.node.dao.model.PartnerConfig;
import com.mfs.node.dao.model.PartnerCurbi;

public interface PartnerConfigDao extends BaseDao {
	public List<PartnerConfig> getDetails();

	public List<Countries> getCountries();

	public List<Currencies> getCurrencies();

	public List<MethodType> getMethodType();

	public List<JarParameters> getAllParametersName();

	int getPartnerCurbi(String partnerCode) throws Exception;

	boolean addPartnerCurbi(PartnerCurbi partnerCurbi) throws Exception;

	boolean updatePartnerCurbi(PartnerCurbi partnerCurbi) throws Exception;
}
