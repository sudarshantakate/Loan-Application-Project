package com.ltifinance.loanapp.loanservice.entity;

import lombok.Data;

@Data
public class LoanEvent {

	private Long loanId;
	private Long customerId;
	private Double amount;
	private String status;
	
	public LoanEvent(Long loanId, Long customerId, Double amount, String status) {
		super();
		this.loanId = loanId;
		this.customerId = customerId;
		this.amount = amount;
		this.status = status;
	}
	
	
}
