package com.mfs.node.dao;

import com.mfs.node.dao.dto.NodeConfigRequestDto;
import com.mfs.node.dao.model.PartnerCurbi;

public interface RemoteDbDao {
	// PartnerConfig
	boolean addPartnerConfig(NodeConfigRequestDto dto) throws Exception;

	boolean updatePartnerConfig(NodeConfigRequestDto dto) throws Exception;

	int getPartnerConfig(String partnerCode, String method) throws Exception;

	// CashPickupPartner
	boolean addCashPickupPartner(String partnerCode, String reference) throws Exception;

	boolean updateCashPickupPartner(String partnerCode, String reference) throws Exception;

	int getCashPickupPartner(String partnerCode) throws Exception;

	// PartnerCurbi
	boolean addPartnerCurbi(PartnerCurbi partnerCurbi) throws Exception;

	boolean updatePartnerCurbi(PartnerCurbi partnerCurbi) throws Exception;

	int getPartnerCurbi(String partnerCode) throws Exception;

	// OVA
	int getOVA(String spCode, String rpCode) throws Exception;

	boolean addOVA(String spCode, String rpCode, String ova) throws Exception;

	boolean updateOVA(String spCode, String rpCode, String ova) throws Exception;
}
