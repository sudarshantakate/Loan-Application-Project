package com.ltifinance.loanapp.customerservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ltifinance.loanapp.customerservice.dto.ErrorResponse;
@RestControllerAdvice  
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex) {

		ErrorResponse error = new ErrorResponse(ex.getMessage(), 
				HttpStatus.NOT_FOUND.value(), 
				LocalDateTime.now());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {

		ErrorResponse error = new ErrorResponse(ex.getMessage(), 
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				LocalDateTime.now());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
