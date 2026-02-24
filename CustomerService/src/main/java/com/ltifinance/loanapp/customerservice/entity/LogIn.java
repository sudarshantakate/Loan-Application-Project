package com.ltifinance.loanapp.customerservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ltifinance.loanapp.customerservice.dto.LogInDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class LogIn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true)
	private String username;

	private String email;

	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	private Customer customer;

	private String roleName;

	public LogInDto convertLoginToLogindto(LogIn login) {
		return LogInDto.builder().id(login.getId()).username(login.getUsername()).email(login.getEmail()).build();
	}
}
