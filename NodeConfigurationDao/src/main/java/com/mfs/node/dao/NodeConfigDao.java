package com.mfs.node.dao;

import java.util.List;

import com.mfs.node.dao.model.CashPickupPartner;
import com.mfs.node.dao.model.OVA;
import com.mfs.node.dao.model.PartnerConfig;
import com.mfs.node.dao.model.User;

public interface NodeConfigDao extends BaseDao {

	public User login(String userName) throws Exception;

	public PartnerConfig nodeConfigByPartnerCode(String partnerCode, String method) throws Exception;

	public User verifyOtp(String otp, String userName) throws Exception;

	public User getUserByUserName(String userName) throws Exception;

	public CashPickupPartner getCashPickupPartner(String partnerCode) throws Exception;

	public OVA getOVABySPAndRP(String SPCode, String RPCode) throws Exception;

	public List<OVA> getAllOVA() throws Exception;

}
