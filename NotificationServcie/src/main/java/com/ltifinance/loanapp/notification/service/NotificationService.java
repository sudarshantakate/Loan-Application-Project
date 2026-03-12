package com.ltifinance.loanapp.notification.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	public void sendNotification(String message) {

		System.out.println("Notification Sent : " + message);
	}
}
