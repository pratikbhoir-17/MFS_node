package com.mfs.node.dao.dto;

public class MethodDto {
	private String methodName;

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	@Override
	public String toString() {
		return "MethodDto [methodName=" + methodName + "]";
	}

}
