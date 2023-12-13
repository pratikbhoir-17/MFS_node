package com.mfs.node.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "system_config")
public class SystemConfig {
	@Id
	@GeneratedValue
	@Column(name = "system_config_id")
	private int systemConfigId;

	@Column(name = "config_key",nullable = false)
	private String configKey;

	@Column(name = "config_value",nullable = false)
	private String configValue;

	public int getSystemConfigId() {
		return systemConfigId;
	}

	public void setSystemConfigId(int systemConfigId) {
		this.systemConfigId = systemConfigId;
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	@Override
	public String toString() {
		return "SystemConfig [systemConfigId=" + systemConfigId + ", configKey=" + configKey + ", configValue="
				+ configValue + "]";
	}

}