package com.ltifinance.loanapp.loanservice.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltifinance.loanapp.loanservice.entity.LoanEvent;

import org.springframework.kafka.core.KafkaTemplate;

@Service
public class LoanEventProducer {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void publishLooanEvent(LoanEvent event) {
		kafkaTemplate.send("notification-topic", event);
		System.out.println("Loan event publish to kafka");
	}
}
