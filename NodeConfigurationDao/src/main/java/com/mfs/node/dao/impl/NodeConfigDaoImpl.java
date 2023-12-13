package com.mfs.node.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mfs.node.dao.NodeConfigDao;
import com.mfs.node.dao.model.CashPickupPartner;
import com.mfs.node.dao.model.OVA;
import com.mfs.node.dao.model.PartnerConfig;
import com.mfs.node.dao.model.User;

@Repository
@EnableTransactionManagement
public class NodeConfigDaoImpl extends BaseDaoImpl implements NodeConfigDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger LOGGER = Logger.getLogger(NodeConfigDaoImpl.class);

	@Transactional
	public User login(String userName) throws Exception {
		User user = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			user = (User) session.createQuery("From User where userName=:userName").setParameter("userName", userName)
					.uniqueResult();
		} catch (Exception e) {
			LOGGER.error("==>Exception in class: NodeConfigDaoImpl method: login ", e);
			throw new Exception();

		}

		return user;
	}

	@Transactional
	public PartnerConfig nodeConfigByPartnerCode(String partnerCode, String method) throws Exception {
		PartnerConfig conf = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			conf = (PartnerConfig) session
					.createQuery("From PartnerConfig  where partnerCode=:partnerCode and method=:method")
					.setParameter("partnerCode", partnerCode).setParameter("method", method).uniqueResult();
		} catch (Exception e) {
			LOGGER.error("==>Exception in class: NodeConfigDaoImpl method: nodeConfigByPartnerCode ", e);
			throw new Exception();

		}
		return conf;
	}

	@Transactional
	public User verifyOtp(String otp, String userName) throws Exception {
		User user = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			user = (User) session.createQuery("From User where userName=:userName and otp=:otp")
					.setParameter("userName", userName).setParameter("otp", otp).uniqueResult();
		} catch (Exception e) {
			LOGGER.error("==>Exception in class: NodeConfigDaoImpl method: verifyOtp ", e);
			throw new Exception();

		}

		return user;
	}

	@Transactional
	public User getUserByUserName(String userName) throws Exception {
		User user = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			user = (User) session.createQuery("From User  where userName=:userName").setParameter("userName", userName)
					.uniqueResult();
		} catch (Exception e) {
			LOGGER.error("==>Exception in class: NodeConfigDaoImpl method: getUserByUserName ", e);
			throw new Exception();

		}
		return user;
	}

	@Transactional
	public CashPickupPartner getCashPickupPartner(String partnerCode) throws Exception {

		CashPickupPartner cashPickupPartners = null;
		Session session = sessionFactory.getCurrentSession();
		try {

			cashPickupPartners = (CashPickupPartner) session.createQuery("From CashPickupPartner  where spCode=:spCode")
					.setParameter("spCode", partnerCode).uniqueResult();

		} catch (Exception e) {
			LOGGER.error("==>Exception in class: NodeConfigDaoImpl method: getCashPickupPartner ", e);
			throw new Exception();

		}
		return cashPickupPartners;
	}

	@Transactional
	public OVA getOVABySPAndRP(String SPCode, String RPCode) throws Exception {
		OVA ova = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			ova = (OVA) session.createQuery("From OVA where SPCode=:SPCode and RPCode=:RPCode")
					.setParameter("SPCode", SPCode).setParameter("RPCode", RPCode).uniqueResult();
		} catch (Exception e) {
			LOGGER.error("==>Exception in class: NodeConfigDaoImpl method: getOVABySPAndRP() ", e);
			throw new Exception();
		}
		return ova;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<OVA> getAllOVA() throws Exception {
		List<OVA> ovaList = new ArrayList<OVA>();
		try {
			Session session = sessionFactory.getCurrentSession();
			ovaList = session.createQuery("From OVA").list();
		} catch (Exception e) {
			LOGGER.error("==>Exception in class: NodeConfigDaoImpl method: getAllOVA() ", e);
			throw new Exception();
		}
		return ovaList;
	}

}
