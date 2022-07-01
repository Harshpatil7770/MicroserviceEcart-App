package com.ecart.delivery.msgsender;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Bean
	public Queue addNewCategory() {
		return new Queue("NewCategoryQ", false);
	}

	public void sendNewCategoryAddDetails(String msg) {
		rabbitTemplate.convertAndSend("NewCategoryQ", msg);
	}

	@Bean
	public Queue addOrUpdateCategory() {
		return new Queue("addOrUpdateQ", false);
	}

	public void sendNewaddOrUpdateCategoryDetails(String msg) {
		rabbitTemplate.convertAndSend("addOrUpdateQ", msg);
	}

	@Bean
	public Queue addNewListsOfCategory() {
		return new Queue("NewCategoryListsQ", false);
	}

	public void addNewListsOfCategory(String msg) {
		rabbitTemplate.convertAndSend("NewCategoryListsQ", msg);
	}

	@Bean
	public Queue addNewOrUpdateListsOfCategory() {
		return new Queue("addOrUpdateCategoryListsQ", false);
	}

	public void addNewOrUpdateListsOfCategory(String msg) {
		rabbitTemplate.convertAndSend("addOrUpdateCategoryListsQ", msg);
	}

	@Bean
	public Queue deleteCategory() {
		return new Queue("deleteCategoryQ", false);
	}

	public void deleteCategory(String msg) {
		rabbitTemplate.convertAndSend("deleteCategoryQ", msg);
	}
}
