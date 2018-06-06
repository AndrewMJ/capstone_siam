package com.siam.model;

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
}
