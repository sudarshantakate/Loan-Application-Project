package com.ltifinance.loanapp.kafkaservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

	@KafkaListener(topics = "ltiloan-topic", groupId = "sudarshan-group")
	public void consume(String message) {
		System.out.println("Consuming Message: " + message);
	}
}
