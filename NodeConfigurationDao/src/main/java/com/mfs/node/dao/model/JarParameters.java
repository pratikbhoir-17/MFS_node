package com.mfs.node.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jar_parameters")
public class JarParameters {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "jar_parameters_name", nullable = false)
	private String jarParametersName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJarParametersName() {
		return jarParametersName;
	}

	public void setJarParametersName(String jarParametersName) {
		this.jarParametersName = jarParametersName;
	}

	@Override
	public String toString() {
		return "JarParameters [id=" + id + ", jarParametersName=" + jarParametersName + "]";
	}

}
