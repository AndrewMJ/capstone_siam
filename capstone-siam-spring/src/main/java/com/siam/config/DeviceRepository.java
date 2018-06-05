package com.siam.config;

import org.springframework.data.repository.CrudRepository;

import com.siam.model.Device;

public interface DeviceRepository extends CrudRepository<Device, Long>{
	
}