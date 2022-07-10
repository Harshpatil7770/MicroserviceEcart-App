package com.example.demo.msgsender;

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
	public Queue addNewProduct() {
		return new Queue("addNewProductQ", false);
	}

	public void addNewProduct(String msg) {
		rabbitTemplate.convertAndSend("addNewProductQ", msg);
	}

	@Bean
	public Queue updateProduct() {
		return new Queue("updateProductQ", false);
	}

	public void updateExisingProduct(String msg) {
		rabbitTemplate.convertAndSend("updateProductQ", msg);
	}

	@Bean
	public Queue saveOrUpdateListsOfProducts() {
		return new Queue("saveOrUpdateListsOfProductsQ", false);
	}

	public void saveOrUpdateListsOfProducts(String msg) {
		rabbitTemplate.convertAndSend("saveOrUpdateListsOfProductsQ", msg);
	}

}
