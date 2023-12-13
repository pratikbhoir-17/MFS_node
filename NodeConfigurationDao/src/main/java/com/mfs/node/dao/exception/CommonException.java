package com.mfs.node.dao.exception;

import com.mfs.node.dao.dto.ResponseStatus;

public class CommonException extends Exception {

	private static final long serialVersionUID = 1L;

	private ResponseStatus status;

	public CommonException(String message) {
		super(message);
	}

	public CommonException(ResponseStatus status) {

		super(status.getStatusMessage());
		this.status = status;
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DaoException [status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}
