package com.ltifinance.loanapp.loanservice.exceptionHandling;

public class resourceNotFoundException extends RuntimeException {

    public resourceNotFoundException(String message) {
        super(message);
    }
}