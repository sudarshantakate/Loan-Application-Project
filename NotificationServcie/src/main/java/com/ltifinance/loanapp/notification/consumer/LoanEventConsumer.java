package com.ltifinance.loanapp.notification.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.ltifinance.loanapp.notification.entity.LoanEvent;
import com.ltifinance.loanapp.notification.service.NotificationService;

@Component
public class LoanEventConsumer {

	@Autowired
	private NotificationService notificationService;

	@KafkaListener(topics = "notification-topic", groupId = "notification-group")
	public void consumeLoanEvent(LoanEvent event) {

		System.out.println("Loan Event Received");

		System.out.println("Loan ID: " + event.getLoanId());
		System.out.println("Customer ID: " + event.getCustomerId());
		System.out.println("Amount: " + event.getAmount());
		System.out.println("Status: " + event.getStatus());

		String message = "Loan " + event.getStatus() + " for Loan Id : " + event.getLoanId();

		notificationService.sendNotification(message);
	}

}
