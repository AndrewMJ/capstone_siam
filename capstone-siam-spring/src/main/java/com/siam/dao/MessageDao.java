package com.siam.dao;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.siam.config.MessageRepository;
import com.siam.model.Message;

@Component
public class MessageDao {
	
	@Autowired
	private MessageRepository messageRepository;
	private final String GET_MESSAGE = "SELECT * FROM message ";
	private final String GET_ID = "SELECT id FROM message ";
	private final String WHERE_MAC = "WHERE macaddr=";
	private final String WHERE_IP = "WHERE ipaddr=";
	private final String INSERT_DEVICE = "INSERT INTO message (macaddr, ipaddr, success, message, rtt, date) VALUES (?,?,?,?,?,?)";
	private final String UPDATE_PATH = "UPDATE message SET path=? ";
	private final String UPDATE_MESSAGE = "UPDATE message SET ipaddr=?, success=?, message=?, rtt=?, date=? ";
	private final String DELETE_MESSAGE = "DELETE FROM messsage WHERE id=";
	private final String COUNT_MESSAGE = "SELECT COUNT(*) FROM message ";
	private final Logger LOGGER = LoggerFactory.getLogger(MessageDao.class);

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Message getMessageByIp(String ipaddr) {
		return jdbcTemplate.queryForObject(GET_MESSAGE + WHERE_IP + "\'" + ipaddr + "\'", new BeanPropertyRowMapper<>(Message.class));
	}
	
	public Message getMessageByMac(String macaddr) {
		return jdbcTemplate.queryForObject(GET_MESSAGE + WHERE_MAC + "\'" + macaddr + "\'", new BeanPropertyRowMapper<>(Message.class));
	}
	
	public Integer getIdByMac(String macaddr) {
		return jdbcTemplate.queryForObject(GET_ID + WHERE_MAC + "\'" + macaddr + "\'", Integer.class);
	}
	
	public Integer countMessageByMac(String macaddr) {
		return jdbcTemplate.queryForObject(COUNT_MESSAGE + WHERE_MAC + "\'" + macaddr + "\'", Integer.class);
	}
	
	public int insertMessage(String macaddr, String ipaddr, int success, String message, int rtt) {
		LOGGER.info("received: " + macaddr + " " + ipaddr + " " + success + " " + message);
		return jdbcTemplate.update(INSERT_DEVICE, new Object[] {macaddr, ipaddr, success, message, rtt, new Date()});
	}
	
	public int updatePath(String macaddr, String path) {
		LOGGER.info("received: " + path);
		return jdbcTemplate.update(UPDATE_PATH + WHERE_MAC + "\'" + macaddr + "\'", new Object[] {path});
	}
	
	public int updateMessage(String macaddr, String ipaddr, int success, String message, int rtt) {
		LOGGER.info("received: " + message + " of IP address of " + ipaddr);
		return jdbcTemplate.update(UPDATE_MESSAGE + WHERE_MAC + "\'" + macaddr + "\'", new Object[] {ipaddr, success, message, rtt, new Date()});
	}
	
	public int deleteMessageById(Integer id) {
		return jdbcTemplate.update(DELETE_MESSAGE + id);
	}
	
	public void deleteAllMessages() {
		messageRepository.deleteAll();
	}
	
	public Iterable<Message> getAllMessages() {
		return messageRepository.findAll();
	}
	
}
