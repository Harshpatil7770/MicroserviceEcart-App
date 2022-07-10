package com.example.demo.resource;

import java.util.logging.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;

@Controller
public class MessageListner {

	private Logger logger = Logger.getLogger(MessageListner.class.getName());

	@RabbitListener(queues = "updateProductQ")
	public void saveOrUpdateTheProduct(String msg) {
		logger.info("updateProductQ Msg Listened ");
		System.out.println(msg);
	}

	@RabbitListener(queues = "saveOrUpdateListsOfProductsQ")
	public void saveOrUpdateListsOfProducts(String msg) {
		logger.info("saveOrUpdateListsOfProductsQ Msg Listened ");
		System.out.println(msg);
	}

	@RabbitListener(queues = "addNewProductQ")
	public void addNewProductQ(String msg) {
		logger.info("addNewProductQ Msg Listened ");
		System.out.println(msg);
	}

}
