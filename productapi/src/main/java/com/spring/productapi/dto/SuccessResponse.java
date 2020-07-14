package com.spring.productapi.dto;

public class SuccessResponse {
	private long id;
	private String message;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMsg() {
		return message;
	}
	public void setMsg(String msg) {
		this.message = msg;
	}
	@Override
	public String toString() {
		return "SuccessResponse [id=" + id + ", msg=" + message + "]";
	}
}

