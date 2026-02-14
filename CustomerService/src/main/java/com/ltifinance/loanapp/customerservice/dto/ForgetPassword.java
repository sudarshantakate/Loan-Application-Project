package com.ltifinance.loanapp.customerservice.dto;

import lombok.Data;

@Data
public class ForgetPassword {

	private String username;

	private String email;

	private String newpassword;

	private String confirmPassword;
}
