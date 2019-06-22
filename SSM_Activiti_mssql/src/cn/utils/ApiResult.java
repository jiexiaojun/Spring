package cn.utils;

import java.io.Serializable;

import com.jfinal.kit.JsonKit;

public class ApiResult implements Serializable{
	
	private static final long serialVersionUID = -6796861780424019296L;
	private int success;
	private String msg;
	private Object data;
	private int total;
		
	public ApiResult() {
	}

	public ApiResult(int success, String msg) {
		this();
		this.success = success;
		this.msg = msg;
	}
	
	
	public String toJson() {
		return JsonKit.toJson(this);
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
