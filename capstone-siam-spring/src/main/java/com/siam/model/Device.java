package com.siam.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Device {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	private String macaddr;
	private String ipaddr;
	private String company;
	private String type;
	private Integer connected;
	private Date last_connected;
	
	public Integer getConnected() {
		return connected;
	}

	public void setConnected(Integer connected) {
		this.connected = connected;
	}

	public Date getLast_connected() {
		return last_connected;
	}

	public void setLast_connected(Date last_connected) {
		this.last_connected = last_connected;
	}

	public Integer getId() {
		return id;
	}
	
	public String getIpaddr() {
		return ipaddr;
	}
	
	public String getCompany() {
		return company;
	}
	
	public String getMacaddr() {
		return macaddr;
	}
	
	public String getType() {
		return type;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	public void setMacaddr(String macaddr) {
		this.macaddr = macaddr;
	}
	
	public void setType(String type) {
		this.type = type;
	}

}
