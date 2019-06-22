package com.allen.utils;

import java.io.Serializable;

public class Result implements Serializable {

	private static final long serialVersionUID = -6796861780424019296L;
	private boolean error;
	private String msg;
	private Object rows;
	private Integer total;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public boolean isError() {
		return error;
	}

	public String getMsg() {
		return msg;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}
}
