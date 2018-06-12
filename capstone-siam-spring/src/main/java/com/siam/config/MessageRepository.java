package com.siam.config;

import org.springframework.data.repository.CrudRepository;

import com.siam.model.Message;

public interface MessageRepository extends CrudRepository<Message, Long>{
	
}