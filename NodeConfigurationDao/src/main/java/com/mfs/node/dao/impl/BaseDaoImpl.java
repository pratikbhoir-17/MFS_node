package com.mfs.node.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mfs.node.dao.BaseDao;

public class BaseDaoImpl implements BaseDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger LOGGER = Logger.getLogger(BaseDaoImpl.class);

	@Transactional
	public boolean save(Object obj) {
		// LOGGER.debug("Inside BaseDAO Save");

		boolean isSuccess = false;
		try {
			this.sessionFactory.getCurrentSession().save(obj);
			isSuccess = true;
		} catch (Exception e) {

			LOGGER.error("==>Exception thrown in BaseDaoImpl in save ", e);

		}
		return isSuccess;
	}

	@Transactional
	public boolean saveOrUpdate(Object obj) {
		// LOGGER.debug("Inside BaseDAO saveOrUpdate");
		boolean isSuccess = false;
		try {
			this.sessionFactory.getCurrentSession().saveOrUpdate(obj);
			isSuccess = true;
		} catch (Exception e) {
			LOGGER.error("==>Exception in BaseDaoImpl in saveOrUpdate ", e);

		}
		return isSuccess;
	}

	@Transactional
	public boolean update(Object obj){
		// LOGGER.debug("Inside BaseDAO Update");
		boolean isSuccess = false;
		try {
			this.sessionFactory.getCurrentSession().update(obj);
			isSuccess = true;
		} catch (Exception e) {
			LOGGER.error("==>Exception thrown in BaseDaoImpl in update", e);

		}
		return isSuccess;
	}

	@Transactional
	public long saveTransfer(Object obj){
		// LOGGER.debug("Inside BaseDAO Save");
		long key = 0;
		try {
			key = (Integer) this.sessionFactory.getCurrentSession().save(obj);
		} catch (Exception e) {
			LOGGER.error("==>Exception thrown in BaseDaoImpl in save ", e);

		}
		return key;
	}
}
