package com.siam.services;

import java.util.ArrayList;
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
import com.siam.model.Message;

@Service
public class MessageService {
	
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private AsyncService asyncService;
	private IcmpPingRequest request;
	private IcmpPingResponse response;
	private final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);
	
	public Iterable<Device> getAllDevices() {
		return deviceDao.getAllDevices();
	}
	
	public Iterable<Message> getAllMessages() {
		return messageDao.getAllMessages();
	}
	
	public void results() {
//		messageDao.deleteAllMessages();
		ArrayList<Device> deviceList = (ArrayList<Device>) deviceDao.getAllConnectedDevices();
		Iterator<Device> deviceIter = deviceList.iterator();
		LOGGER.info("iterating all devices in db.....");
		while(deviceIter.hasNext()) {
			Device device = deviceIter.next();
			asyncService.run(device);
		}
	}

}
