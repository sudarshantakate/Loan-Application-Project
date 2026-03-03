package com.ltifinance.loanapp.loanservice.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ltifinance.loanapp.loanservice.constant.EnumData;

import lombok.Data;

@Data
@Entity(name = "Loan")
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanId;

	private Long customerId;

	private Double loanAmount;

	private Integer tenureMonths;

	private Double emiAmount;

	private String loanType;
	
//	@Enumerated(EnumType.STRING)
	private String status;
	
	private LocalDate applicationDate;

	private LocalDate approvedDate;

	@Column(nullable = false)
	private Boolean deleted = false;
}
