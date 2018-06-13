package com.siam.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private final String COUNT_DEVICE = "SELECT COUNT(*) FROM device ";
	private final String WHERE_MAC = "WHERE macaddr=";
	private final String WHERE_IP = "WHERE ipaddr=";
	private final String WHERE_CON = "WHERE connected=\'1\'";
	private final String INSERT_DEVICE = "INSERT INTO device (macaddr, ipaddr, company, type, connected, last_connected) VALUES (?,?,?,?,?,?)";
	private final String UPDATE_IP = "UPDATE device SET ipaddr=?, connected=?, last_connected=? ";
	private final String UPDATE_CON = "UPDATE device SET connected=?, last_connected=? ";
	private final String UPDATE_CONNECTED = "UPDATE device SET connected=\'0\' WHERE macaddr IS NOT NULL";
	private final String DELETE_DEVICE = "DELETE FROM device WHERE id=";
	private final Logger LOGGER = LoggerFactory.getLogger(DeviceDao.class);

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Device getDeviceByIp(String ipaddr) {
		return jdbcTemplate.queryForObject(GET_DEVICE + WHERE_IP + "\'" + ipaddr + "\'", new BeanPropertyRowMapper<>(Device.class));
	}
	
	public Device getDeviceByMac(String macaddr) {
		return jdbcTemplate.queryForObject(GET_DEVICE + WHERE_MAC + "\'" + macaddr + "\'", new BeanPropertyRowMapper<>(Device.class));
	}
	
	public Integer countDeviceByIp(String ipaddr) {
		return jdbcTemplate.queryForObject(COUNT_DEVICE + WHERE_MAC + "\'" + ipaddr + "\'", Integer.class);
	}
	
	public Integer countDeviceByMac(String macaddr) {
		return jdbcTemplate.queryForObject(COUNT_DEVICE + WHERE_MAC + "\'" + macaddr + "\'", Integer.class);
	}
	
	public int insertDevice(String macaddr, String ipaddr, String company, String type, int connected) {
		LOGGER.info("received: " + macaddr + " " + ipaddr + " " + company + " " + type);
		return jdbcTemplate.update(INSERT_DEVICE, new Object[] {macaddr, ipaddr, company, type, connected, new Date()});
	}
	
	public int updateDevice(String macaddr, String ipaddr) {
		LOGGER.info("received: " + ipaddr + " as IP address of " + macaddr);
		return jdbcTemplate.update(UPDATE_IP + WHERE_MAC + "\'" + macaddr + "\'", new Object[] {ipaddr, 1, new Date()});
	}
	
	public int updateConnectedDevice(String macaddr) {
		LOGGER.info("connected: " + macaddr);
		return jdbcTemplate.update(UPDATE_CON + WHERE_MAC + "\'" + macaddr + "\'", new Object[] {1, new Date()});
	}
	
	public int updateConnected() {
		LOGGER.info("All connection is set to 0");
		return jdbcTemplate.update(UPDATE_CONNECTED);
	}
	
	public int deleteDeviceById(Integer id) {
		return jdbcTemplate.update(DELETE_DEVICE + id);
	}
	
	public void deleteAllDevices() {
		deviceRepository.deleteAll();
	}
	
	public List<Device> getAllConnectedDevices() {
		return jdbcTemplate.query(GET_DEVICE + WHERE_CON, new BeanPropertyRowMapper<>(Device.class));
	}
	
	public Iterable<Device> getAllDevices() {
		return deviceRepository.findAll();
	}
}
