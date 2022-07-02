package com.ecart.delivery.msgsender;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Bean
	public Queue addNewBrand() {
		return new Queue("NewBrandQ",false);
	}
	
	public void addNewBrand(String msg) {
		rabbitTemplate.convertAndSend("NewBrandQ",msg);
	}
}
