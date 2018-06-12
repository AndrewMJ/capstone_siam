package com.siam.controller;

import com.siam.model.Device;
import com.siam.model.Message;
import com.siam.services.DeviceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

	@CrossOrigin
	@GetMapping(path="/data")
	public @ResponseBody ArrayList<Message> getDataFromPing() {
		return deviceServices.results();
	}
	
	@RequestMapping(value="data/{host}", method=RequestMethod.GET)
	public @ResponseBody Message getDataFromSingleDevice(@PathVariable("host") String host) {
		return deviceServices.singleDevice(host);
	}
}
