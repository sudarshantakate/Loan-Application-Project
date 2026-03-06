package com.ltifinance.loanapp.kafkaservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ltifinance.loanapp.kafkaservice.producer.KafkaProducerService;

@RestController
public class MessageController {

	@Autowired
	private KafkaProducerService kafkaProducerService;

	public String sendMessage(@PathVariable String message) {
		kafkaProducerService.sendMessage(message);
		return "Message Send To kafka Successfully " + message;
	}
}
