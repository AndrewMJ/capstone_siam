package com.siam.services;

import java.util.Iterator;

import org.icmp4j.IcmpPingRequest;
import org.icmp4j.IcmpPingResponse;
import org.icmp4j.IcmpPingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siam.dao.DeviceDao;
import com.siam.dao.MessageDao;
import com.siam.model.Device;

@Service
public class MessageService {
	
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private MessageDao messageDao;
	private IcmpPingRequest request;
	private IcmpPingResponse response;
	private final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);
	
	public Iterable<Device> getAllDevices() {
		return deviceDao.getAllDevices();
	}
	
	public void results() {
		Iterable<Device> deviceList = getAllDevices();
		Iterator<Device> deviceIter = deviceList.iterator();
		LOGGER.info("iterating all devices in db.....");
		while(deviceIter.hasNext()) {
			Device device = deviceIter.next();
			String macaddr = device.getMacaddr();
			String host = device.getIpaddr();
			request = IcmpPingUtil.createIcmpPingRequest();
			request.setHost(host);
			response = IcmpPingUtil.executePingRequest(request);
			boolean successFlag = response.getSuccessFlag();
			int success;
			if(successFlag) success = 1;
			else success = 0;
			String message = response.getErrorMessage();
			String hostMsg = "IP ADDRESS: " + host;
			String successMsg = "SUCCESS: " + successFlag;
			String messageFromPing = "MESSAGE: " + message;
			LOGGER.info(hostMsg);
			LOGGER.info(successMsg);
			LOGGER.info(messageFromPing);
			if(messageDao.countMessageByMac(macaddr) == 0) messageDao.insertMessage(macaddr, host, success, messageFromPing);
			else messageDao.updateMessage(macaddr, host, success, messageFromPing);
		}
	}

}
