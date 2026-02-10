package com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.dto.EmailDto;
import com.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendMail(EmailDto emaildto) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(emaildto.getTo());
		message.setSubject(emaildto.getSubject());
		message.setText(emaildto.getBody());

		mailSender.send(message);
	}
}
