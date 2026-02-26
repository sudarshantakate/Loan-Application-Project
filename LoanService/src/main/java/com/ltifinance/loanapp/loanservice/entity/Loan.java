package com.ltifinance.loanapp.loanservice.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanId;

	private Long customerId;

	private Double loanAmount;

	private Integer tenureMonths;

	private Double emiAmount;

	private String loanType;

	private String status;

	private LocalDate applicationDate;

	private LocalDate approvedDate;

	@Column(nullable = false)
	private Boolean deleted = false;
}
