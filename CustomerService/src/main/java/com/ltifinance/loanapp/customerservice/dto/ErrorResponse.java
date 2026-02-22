package com.ltifinance.loanapp.customerservice.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

	private String message;
	private int status;
	private LocalDateTime timestamp;

}
