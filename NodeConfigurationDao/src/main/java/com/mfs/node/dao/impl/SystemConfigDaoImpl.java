package com.mfs.node.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mfs.node.dao.SystemConfigDao;
import com.mfs.node.dao.model.SystemConfig;

@EnableTransactionManagement
@Repository
public class SystemConfigDaoImpl implements SystemConfigDao {

	private static final Logger LOGGER = Logger.getLogger(SystemConfigDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public Map<String, String> getConfigDetailsMap() {
		Map<String, String> systemConfigMap = null;

		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "From SystemConfig";
			Query query = session.createQuery(hql);

			List<SystemConfig> systemConfig = query.list();

			if (systemConfig != null && !systemConfig.isEmpty()) {
				systemConfigMap = new HashMap<String, String>();
				for (SystemConfig systemConfiguration : systemConfig) {
					systemConfigMap.put(systemConfiguration.getConfigKey(), systemConfiguration.getConfigValue());
				}
			}
		} catch (Exception e) {
			LOGGER.error("==>CommonException in SystemConfigDaoImpl", e);
		}
		return systemConfigMap;
	}

}
