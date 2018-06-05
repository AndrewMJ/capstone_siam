package com.siam.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.siam.model.Device;
import com.siam.model.Message;
import com.siam.services.DeviceServices;

@RestController
public class DeviceController {
	
	@Autowired
	DeviceServices deviceServices;
	
	@RequestMapping(value="/device/{host}", method=RequestMethod.GET)
	public @ResponseBody Device getDeviceByIp(@PathVariable("host") String host) {
		return deviceServices.getDeviceByIp(host);
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Device> getAllDevices() {
		return deviceServices.getAllDevices();
	}
	
	@GetMapping(path="/data")
	public @ResponseBody ArrayList<Message> getDataFromPing() {
		return deviceServices.results();
	}
}
