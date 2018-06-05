package com.siam.model;

import java.util.Date;

public class Message {
	
	private String ipAddr;
	private boolean success;
	private String message;
	private Date date;
	
	public Message(String ipAddr, boolean success, String message, Date date) {
		setIpAddr(ipAddr);
		setSuccess(success);
		setMessage(message);
		setDate(date);
	}
	
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
