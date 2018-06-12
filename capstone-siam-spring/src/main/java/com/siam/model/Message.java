package com.siam.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String macaddr;
	private String ipaddr;
	private int success;
	private String message;
	private int rtt;
	private Date date;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIpAddr() {
		return ipaddr;
	}
	public void setIpAddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	public String getMacAddr() {
		return macaddr;
	}
	public void setMacAddr(String macaddr) {
		this.macaddr = macaddr;
	}
	public int isSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getRtt() {
		return rtt;
	}
	public void setRtt(int rtt) {
		this.rtt = rtt;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
