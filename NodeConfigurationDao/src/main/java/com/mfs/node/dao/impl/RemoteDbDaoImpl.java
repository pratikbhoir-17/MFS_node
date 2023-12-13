package com.mfs.node.dao.impl;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mfs.node.dao.RemoteDbDao;
import com.mfs.node.dao.dto.NodeConfigRequestDto;
import com.mfs.node.dao.model.PartnerCurbi;

@Repository
public class RemoteDbDaoImpl implements RemoteDbDao {
	// @Autowired
	// private JdbcTemplate remoteTemplate;

	@Autowired
	private DataSource remoteDataSource;

	private static final Logger LOGGER = Logger.getLogger(RemoteDbDaoImpl.class);

	public boolean addPartnerConfig(NodeConfigRequestDto dto) throws Exception {
		JdbcTemplate remoteTemplate = new JdbcTemplate(remoteDataSource);
		Object[] args = { dto.getMethod(), dto.getPartnerCode(), dto.getCurrencyCode(), dto.getOperator(),
				dto.getCountryCode(), dto.getMinPerTxLimit(), dto.getMaxPerTxLimit(), dto.getFileName(), dto.getName(),
				dto.getFilePath(), dto.getJarParams(), dto.getPrefix(), dto.getCurbiCode(), dto.getReference() };
		int result = remoteTemplate.update(
				"insert into partner_configs(method,partner_code,currency_code,operator,country_code,min_per_tx_limit,max_per_tx_limit,jar_file_name,name,jar_file_path,jar_params,prefix,curbi_code,reference) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				args);
		if (result == 1)
			return true;
		return false;
	}

	public boolean updatePartnerConfig(NodeConfigRequestDto dto) throws Exception {
		JdbcTemplate remoteTemplate = new JdbcTemplate(remoteDataSource);
		Object[] args = { dto.getMethod(), dto.getCurrencyCode(), dto.getOperator(), dto.getCountryCode(),
				dto.getMinPerTxLimit(), dto.getMaxPerTxLimit(), dto.getFileName(), dto.getName(), dto.getFilePath(),
				dto.getJarParams(), dto.getPrefix(), dto.getCurbiCode(), dto.getReference(), dto.getPartnerCode() };
		int result = remoteTemplate.update(
				"update partner_configs set method=?,currency_code=?,operator=?,country_code=?,min_per_tx_limit=?,max_per_tx_limit=?,jar_file_name=?,name=?,jar_file_path=?,jar_params=?,prefix=?,curbi_code=?,reference=? where partner_code=?",
				args);
		if (result == 1)
			return true;
		return false;
	}

	public int getPartnerConfig(String partnerCode, String method) throws Exception {
		JdbcTemplate remoteTemplate = new JdbcTemplate(remoteDataSource);
		return remoteTemplate.queryForObject(
				"select count(partner_code) from partner_configs where partner_code=? and method=?", Integer.class,
				new Object[] { partnerCode, method });
	}

	public boolean addCashPickupPartner(String partnerCode, String reference) throws Exception {
		JdbcTemplate remoteTemplate = new JdbcTemplate(remoteDataSource);
		Object[] args = { partnerCode, reference };
		int result = remoteTemplate.update("insert into cashpickup_partner(sp_code,reference) values(?,?)", args);
		if (result == 1)
			return true;
		return false;
	}

	public boolean updateCashPickupPartner(String partnerCode, String reference) throws Exception {
		JdbcTemplate remoteTemplate = new JdbcTemplate(remoteDataSource);
		Object[] args = { reference, partnerCode };
		int result = remoteTemplate.update("update cashpickup_partner set reference=? where sp_code=?", args);
		if (result == 1)
			return true;
		return false;
	}

	public int getCashPickupPartner(String partnerCode) throws Exception {
		JdbcTemplate remoteTemplate = new JdbcTemplate(remoteDataSource);
		return remoteTemplate.queryForObject("select count(sp_code) from cashpickup_partner where sp_code=?",
				Integer.class, new Object[] { partnerCode });
	}

	public boolean addPartnerCurbi(PartnerCurbi partnerCurbi) throws Exception {
		JdbcTemplate remoteTemplate = new JdbcTemplate(remoteDataSource);
		Object[] args = { partnerCurbi.getCorporate_code(), partnerCurbi.getCurbi_trxnid(),
				partnerCurbi.getCurrency_code(), partnerCurbi.getCountry_code() };
		int result = remoteTemplate.update(
				"insert into partner_curbi(corporate_code,curbi_trxnid,currency_code,country_code) values(?,?,?,?)",
				args);
		if (result == 1)
			return true;
		return false;
	}

	public boolean updatePartnerCurbi(PartnerCurbi partnerCurbi) throws Exception {
		JdbcTemplate remoteTemplate = new JdbcTemplate(remoteDataSource);
		Object[] args = { partnerCurbi.getCurbi_trxnid(), partnerCurbi.getCurrency_code(),
				partnerCurbi.getCountry_code(), partnerCurbi.getCorporate_code() };
		int result = remoteTemplate.update(
				"update partner_curbi set curbi_trxnid=?,currency_code=?,country_code=? where BINARY corporate_code like ?",
				args);
		if (result == 1)
			return true;
		return false;
	}

	public int getPartnerCurbi(String partnerCode) throws Exception {
		JdbcTemplate remoteTemplate = new JdbcTemplate(remoteDataSource);
		return remoteTemplate.queryForObject(
				"select count(corporate_code) from partner_curbi where BINARY corporate_code like ?", Integer.class,
				new Object[] { partnerCode });
	}

	public int getOVA(String spCode, String rpCode) throws Exception {
		JdbcTemplate remoteTemplate = new JdbcTemplate(remoteDataSource);
		return remoteTemplate.queryForObject("select count(*) from ova where SP_Code=? and RP_Code=?", Integer.class,
				new Object[] { spCode, rpCode });
	}

	public boolean addOVA(String spCode, String rpCode, String ova) throws Exception {
		JdbcTemplate remoteTemplate = new JdbcTemplate(remoteDataSource);
		Object[] args = { spCode, rpCode, ova };
		int result = remoteTemplate.update("insert into ova(SP_Code,RP_Code,ova) values(?,?,?)", args);
		if (result == 1)
			return true;
		return false;
	}

	public boolean updateOVA(String spCode, String rpCode, String ova) throws Exception {
		JdbcTemplate remoteTemplate = new JdbcTemplate(remoteDataSource);
		Object[] args = { ova, spCode, rpCode };
		int result = remoteTemplate.update("update ova set ova=? where SP_Code=? and RP_Code=?", args);
		if (result == 1)
			return true;
		return false;
	}

}
