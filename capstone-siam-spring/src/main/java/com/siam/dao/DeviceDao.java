package com.siam.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.siam.config.DeviceRepository;
import com.siam.model.Device;
import com.siam.services.DeviceServices;

@Component
public class DeviceDao {
	
	@Autowired
	private DeviceRepository deviceRepository;
	private final String GET_DEVICE = "SELECT * FROM device ";
	private final String COUNT_DEVICE = "SELECT COUNT(*) FROM device ";
	private final String WHERE_MAC = "WHERE macaddr=";
	private final String WHERE_IP = "WHERE ipaddr=";
	private final String INSERT_DEVICE = "INSERT INTO device (macaddr, ipaddr, company) VALUES (?,?,?)";
	private final String UPDATE_IP = "UPDATE device SET \'ipaddr\'=";
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
	
	public int insertDevice(String macaddr, String ipaddr, String company) {
		LOGGER.info("received: " + macaddr + " " + ipaddr + " " + company);
		return jdbcTemplate.update(INSERT_DEVICE, new Object[] {macaddr, ipaddr, company});
	}
	
	public int updateDevice(String macaddr, String ipaddr) {
		LOGGER.info("received: " + ipaddr + " as IP address of " + macaddr);
		return jdbcTemplate.update(UPDATE_IP + ipaddr + " " + WHERE_MAC + macaddr);
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
