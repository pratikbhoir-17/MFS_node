package com.mfs.node.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mfs.node.dao.PartnerConfigDao;
import com.mfs.node.dao.model.Countries;
import com.mfs.node.dao.model.Currencies;
import com.mfs.node.dao.model.JarParameters;
import com.mfs.node.dao.model.MethodType;
import com.mfs.node.dao.model.PartnerConfig;
import com.mfs.node.dao.model.PartnerCurbi;

@Repository
public class PartnerConfigDaoImpl extends BaseDaoImpl implements PartnerConfigDao {
	@Autowired
	private SessionFactory sessionFactory;

//	@Autowired
//	private JdbcTemplate template;
	
	@Autowired
	private DataSource localDataSource;
	
	private static final Logger LOGGER = Logger.getLogger(PartnerConfigDaoImpl.class);

	@Transactional
	public List<PartnerConfig> getDetails() {
		List<PartnerConfig> partnerConfigList = new ArrayList<PartnerConfig>();
		try {
			Session session = sessionFactory.getCurrentSession();
			partnerConfigList = (List<PartnerConfig>) session.createQuery("From PartnerConfig").list();

		} catch (Exception e) {
			LOGGER.error("==>Exception occured inside PartnerConfigDaoImpl ==> getDetails()", e);
		}
		return partnerConfigList;
	}

	@Transactional
	public List<Countries> getCountries() {
		List<Countries> countriesList = new ArrayList<Countries>();
		try {
			Session session = sessionFactory.getCurrentSession();
			countriesList = (List<Countries>) session.createQuery("From Countries").list();

		} catch (Exception e) {
			LOGGER.error("==>Exception occured inside NodeConfigDaoImpl ==> getCountries()", e);
		}
		return countriesList;
	}

	@Transactional
	public List<Currencies> getCurrencies() {
		List<Currencies> currenciesList = new ArrayList<Currencies>();
		try {
			Session session = sessionFactory.getCurrentSession();
			currenciesList = (List<Currencies>) session.createQuery("From Currencies").list();
		} catch (Exception e) {
			LOGGER.error("==>Exception occured inside NodeConfigDaoImpl ==> getCurrencies()", e);
		}
		return currenciesList;
	}

	@Transactional
	public List<MethodType> getMethodType() {
		List<MethodType> methodTypeList = new ArrayList<MethodType>();
		try {
			Session session = sessionFactory.getCurrentSession();
			methodTypeList = (List<MethodType>) session.createQuery("From MethodType").list();
		} catch (Exception e) {
			LOGGER.error("==>Exception occured inside PartnerConfigDaoImpl ==> getMethodType()", e);
		}
		return methodTypeList;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<JarParameters> getAllParametersName() {
		List listofParam = new ArrayList<JarParameters>();

		try {
			Session session = sessionFactory.getCurrentSession();
			listofParam = (List<JarParameters>) session.createQuery("From JarParameters").list();

		} catch (Exception e) {
			LOGGER.error("==>Exception occured inside PartnerConfigDaoImpl ==> getAllParametersName()", e);

		}
		return listofParam;
	}

	public boolean addPartnerCurbi(PartnerCurbi partnerCurbi) throws Exception {
		JdbcTemplate template = new JdbcTemplate(localDataSource);
		Object[] args = { partnerCurbi.getCorporate_code(), partnerCurbi.getCurbi_trxnid(),
				partnerCurbi.getCurrency_code(), partnerCurbi.getCountry_code() };
		int result = template.update(
				"insert into partner_curbi(corporate_code,curbi_trxnid,currency_code,country_code) values(?,?,?,?)",
				args);
		if (result == 1)
			return true;
		return false;
	}

	public boolean updatePartnerCurbi(PartnerCurbi partnerCurbi) throws Exception {
		JdbcTemplate template = new JdbcTemplate(localDataSource);
		Object[] args = { partnerCurbi.getCurbi_trxnid(), partnerCurbi.getCurrency_code(),
				partnerCurbi.getCountry_code(), partnerCurbi.getCorporate_code() };
		int result = template.update(
				"update partner_curbi set curbi_trxnid=?,currency_code=?,country_code=? where BINARY corporate_code like ?",
				args);
		if (result == 1)
			return true;
		return false;
	}

	public int getPartnerCurbi(String partnerCode) throws Exception {
		JdbcTemplate template = new JdbcTemplate(localDataSource);
		return template.queryForObject(
				"select count(corporate_code) from partner_curbi where BINARY corporate_code like ?", Integer.class,
				new Object[] { partnerCode });
	}

}
