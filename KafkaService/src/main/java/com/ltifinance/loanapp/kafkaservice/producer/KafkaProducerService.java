package com.ltifinance.loanapp.kafkaservice.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

	@Autowired
	public KafkaTemplate<String, String> kafkatemplate;

	private static final String TOPIC = "test-message";

	public void sendMessage(String message) {
		kafkatemplate.send(TOPIC, message);
		System.out.println("Kafka Message: " + message);

	}
}
