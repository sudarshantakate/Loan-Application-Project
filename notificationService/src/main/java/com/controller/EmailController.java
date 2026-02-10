package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.EmailDto;
import com.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailservice;

	@PostMapping(value = "/api/mail/send")
	public ResponseEntity<?> sendmail(@RequestBody EmailDto emaildto) {
		System.out.println("In Email Controller");
		emailservice.sendMail(emaildto);
		return new ResponseEntity<String>("Mail Send Successfully", HttpStatus.OK);
	}

}
