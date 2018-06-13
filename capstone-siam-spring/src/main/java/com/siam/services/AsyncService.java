package com.siam.services;

import org.icmp4j.IcmpPingRequest;
import org.icmp4j.IcmpPingResponse;
import org.icmp4j.IcmpPingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.siam.dao.DeviceDao;
import com.siam.dao.MessageDao;
import com.siam.model.Device;

@Service
public class AsyncService {
	
	@Autowired
	private MessageDao messageDao;
	private IcmpPingRequest request;
	private IcmpPingResponse response;
	private final Logger LOGGER = LoggerFactory.getLogger(AsyncService.class);
	
	@Async("threadPoolTaskExecutor")
	public void run(Device device) {
		String macaddr = device.getMacaddr();
		String host = device.getIpaddr();
		String path = "log/log";
		request = IcmpPingUtil.createIcmpPingRequest();
		request.setHost(host);
		response = IcmpPingUtil.executePingRequest(request);
		boolean successFlag = response.getSuccessFlag();
		int success;
		if(successFlag) success = 1;
		else success = 0;
		String hostMsg = "IP ADDRESS: " + host;
		String successMsg = "SUCCESS: " + successFlag;
		String messageFromPing = response.getErrorMessage();
		int rtt = response.getRtt();
		if(!successFlag) rtt = 0;
		LOGGER.info(hostMsg);
		LOGGER.info(successMsg);
		LOGGER.info(messageFromPing);
		if(messageDao.countMessageByMac(macaddr) == 0) {
			messageDao.insertMessage(macaddr, host, success, messageFromPing, rtt);
			path += messageDao.getIdByMac(macaddr) + ".txt";
			messageDao.updatePath(macaddr, path);
		}
		else messageDao.updateMessage(macaddr, host, success, messageFromPing, rtt);
	}
}
