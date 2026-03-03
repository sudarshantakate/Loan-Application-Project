package com.ltifinance.loanapp.loanservice.dto;

import lombok.Data;

@Data
public class LoanDTO {

	private Long customerId;
	private Double loanAmount;
	private Integer tenureMonths;
	private String loanType;

}
