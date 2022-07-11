package com.xoriant.ecart.msgsender;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class MessageSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Bean
	public Queue saveOrUpdateProduct() {
		return new Queue("saveOrUpdateProductQ", false);
	}

	public void saveOrUpdateProduct(String msg) {
		rabbitTemplate.convertAndSend("saveOrUpdateProductQ", msg);
	}

	@Bean
	public Queue saveOrUpdateListOfProduct() {
		return new Queue("saveOrUpdateListOfProductQ", false);
	}

	public void saveOrUpdateListOfProduct(String addOrUpdateProductLists) {
		rabbitTemplate.convertAndSend("saveOrUpdateListOfProductQ", addOrUpdateProductLists);
	}
}
