package com.siam.services;

import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;

import org.icmp4j.IcmpPingRequest;
import org.icmp4j.IcmpPingResponse;
import org.icmp4j.IcmpPingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siam.dao.DeviceDao;
import com.siam.model.Device;
import com.siam.model.Message;

@Service
public class DeviceServices {
	
	@Autowired
	private DeviceDao deviceDao;
	private IcmpPingRequest request;
	private IcmpPingResponse response;
	private final Logger LOGGER = LoggerFactory.getLogger(DeviceServices.class);
	
	@PostConstruct
	public void init() throws IOException {
		String subnet = "192.168.88.";
		LOGGER.info("Scanning all ip addresses in " + subnet);
		Runtime rt = Runtime.getRuntime();
    	Process process = rt.exec("nmap -sP " + subnet + "*");
    	BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
    	String ipAddr = null;
		String macAddr = null;
		String company = null;
    	String line;
    	while((line = br.readLine()) != null) {
    		StringTokenizer st = new StringTokenizer(line);
    		while(st.hasMoreTokens()) {
    			String token = st.nextToken();
    			if(token.equals("for")) {
    				ipAddr = st.nextToken().trim();
    			}
    			if(token.equals("Address:")) {
    				macAddr = st.nextToken().trim();
    				company = st.nextToken();
    				while(company.charAt(company.length() - 1) != ')')
    					company += " " + st.nextToken();
    				company = company.substring(1, company.length()-1);
    			}
    			if(ipAddr != null && macAddr != null && company != null) {
    				if(deviceDao.countDeviceByMac(macAddr) == 0) {
    					deviceDao.insertDevice(macAddr, ipAddr, company);
    					LOGGER.info(macAddr + " " + ipAddr + " " + company + " is inserted in DB");
    				} else {
    					// Check if there are same ip addresses!!
    					LOGGER.info("Machine with " + macAddr + " is already in DB");
    				}
	    			ipAddr = null;
	    			macAddr = null;
	    			company = null;
    			}
    		}
    	}
	}
	
	public Device getDeviceByIp(String host) {
		return deviceDao.getDeviceByIp(host);
	}
	
	public Iterable<Device> getAllDevices() {
		return deviceDao.getAllDevices();
	}
	
	public ArrayList<Message> results() {
		ArrayList<Message> results = new ArrayList<>();
		Iterable<Device> deviceList = getAllDevices();
		Iterator<Device> deviceIter = deviceList.iterator();
		LOGGER.info("iterating all devices in db.....");
		while(deviceIter.hasNext()) {
			Device device = deviceIter.next();
			String host = device.getIpaddr();
			request = IcmpPingUtil.createIcmpPingRequest();
			request.setHost(host);
			response = IcmpPingUtil.executePingRequest(request);
			boolean successFlag = response.getSuccessFlag();
			String message = response.getErrorMessage();
			String hostMsg = "IP ADDRESS: " + host;
			String successMsg = "SUCCESS: " + successFlag;
			String messageFromPing = "MESSAGE: " + message;
			LOGGER.info(hostMsg);
			LOGGER.info(successMsg);
			LOGGER.info(messageFromPing);
			results.add(new Message(host, successFlag, messageFromPing, new Date()));
		}
		return results;
	}

}
