package com.ecart.delivery.msgsender;

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
	public Queue addNewBrand() {
		return new Queue("addNewBrandQ", false);
	}

	public void addNewBrand(String msg) {
		rabbitTemplate.convertAndSend("addNewBrandQ", false);
	}

	@Bean
	public Queue addOrUpdateBrand() {
		return new Queue("addOrUpdateBrandQ", false);
	}

	public void addOrUpdateBrand(String msg) {
		rabbitTemplate.convertAndSend("addOrUpdateBrandQ", msg);
	}

	@Bean
	public Queue addNewListsOfBrandsWithSameCategoryName() {
		return new Queue("addNewListsOfBrandsWithSameCategoryNameQ", false);
	}

	public void addNewListsOfBrandsWithSameCategoryName(String msg) {
		rabbitTemplate.convertAndSend("addNewListsOfBrandsWithSameCategoryNameQ", msg);
	}

	@Bean
	public Queue addOrUpdateListsOfBrandsWithSameCategoryName() {
		return new Queue("addOrUpdateListsOfBrandsWithSameCategoryNameQ", false);
	}

	public void addOrUpdateListsOfBrandsWithSameCategoryName(String msg) {
		rabbitTemplate.convertAndSend("addOrUpdateListsOfBrandsWithSameCategoryNameQ", msg);
	}

	@Bean
	public Queue deleteExistingBrand() {
		return new Queue("deleteExistingBrandQ", false);
	}

	public void deleteExistingBrand(String msg) {
		rabbitTemplate.convertAndSend("deleteExistingBrandQ", msg);
	}

}
