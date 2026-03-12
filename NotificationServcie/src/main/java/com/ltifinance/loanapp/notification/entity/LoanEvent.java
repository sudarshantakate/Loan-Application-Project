package com.ltifinance.loanapp.notification.entity;

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

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
