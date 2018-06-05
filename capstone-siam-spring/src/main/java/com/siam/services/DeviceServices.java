package com.siam.services;

import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;

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
	private DeviceDao DeviceDao;
	private IcmpPingRequest request;
	private IcmpPingResponse response;
	private final Logger LOGGER = LoggerFactory.getLogger(DeviceServices.class);
	
	@PostConstruct
	public void init() {
		DeviceDao.deleteAllDevices();
		String subnet = "192.168.88.";
		LOGGER.info("Scanning all ip addresses in " + subnet);
		for(int i = 1; i <= 255; i++) {
			String host = subnet + i;
			request = IcmpPingUtil.createIcmpPingRequest();
			request.setHost(host);
			response = IcmpPingUtil.executePingRequest(request);
			LOGGER.info(host);
			LOGGER.info(response.getSuccessFlag() + "");
			LOGGER.info(response.getErrorMessage());
			
			if(response.getSuccessFlag() == true) {
				DeviceDao.insertDevice(host);
				LOGGER.info(host + " is inserted in DB");
			}
		}
	}
	
	public Device getDeviceByIp(String host) {
		return DeviceDao.getDeviceByIp(host);
	}
	
	public Iterable<Device> getAllDevices() {
		return DeviceDao.getAllDevices();
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
