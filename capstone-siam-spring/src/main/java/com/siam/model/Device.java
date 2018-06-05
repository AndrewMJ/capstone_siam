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
	private String ipaddr;
	private String type;
	
	public Integer getId() {
		return id;
	}
	
	public String getIpaddr() {
		return ipaddr;
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
	
	public void setType(String type) {
		this.type = type;
	}
}
