package com.siam.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.siam.config.DeviceRepository;
import com.siam.model.Device;

@Component
public class DeviceDao {
	
	@Autowired
	private DeviceRepository deviceRepository;
	private final String GET_DEVICE = "SELECT * FROM device ";
	private final String WHERE_IP = "WHERE ipaddr=";
	private final String INSERT_DEVICE = "INSERT INTO device (ipaddr, type) VALUES (?,\'PC\')";
	private final String DELETE_DEVICE = "DELETE FROM device WHERE id=";

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Device getDeviceByIp(String host) {
		return jdbcTemplate.queryForObject(GET_DEVICE + WHERE_IP + "\'" + host + "\'", new BeanPropertyRowMapper<>(Device.class));
	}
	
	public int insertDevice(String host) {
		return jdbcTemplate.update(INSERT_DEVICE, host);
	}
	
	public int deleteDeviceById(Integer id) {
		return jdbcTemplate.update(DELETE_DEVICE + id);
	}
	
	public void deleteAllDevices() {
		deviceRepository.deleteAll();
	}
	
	public Iterable<Device> getAllDevices() {
		return deviceRepository.findAll();
	}
}
