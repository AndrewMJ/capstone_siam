package com.siam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.siam.model.Device;
import com.siam.services.DeviceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import com.siam.services.MessageService;

@RestController
public class DeviceController {
	
	@Autowired
	DeviceServices deviceServices;
	@Autowired
	MessageService messageService;
	
	@RequestMapping(value="/device/{host}", method=RequestMethod.GET)
	public @ResponseBody Device getDeviceByIp(@PathVariable("host") String host) {
		return deviceServices.getDeviceByIp(host);
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Device> getAllDevices() {
		return deviceServices.getAllDevices();
	}

	@CrossOrigin
	@GetMapping(path="/data")
	public @ResponseBody ArrayList<Message> getDataFromPing() {
		return deviceServices.results();
	}
	
	@RequestMapping(value="data/{host}", method=RequestMethod.GET)
	public @ResponseBody Message getDataFromSingleDevice(@PathVariable("host") String host) {
		return deviceServices.singleDevice(host);
	}
	
	@RequestMapping(value="/data")
	public void getDataFromPing() {
		messageService.results();
	}

}
