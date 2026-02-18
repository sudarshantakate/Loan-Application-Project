package com.ltifinance.loanapp.customerservice.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ltifinance.loanapp.customerservice.entity.LogIn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = Include.NON_EMPTY)
public class CustomerDto {

	private String fname;
    private String lname;
    private String address;
    private String username;

    private String email;
    private String mobileNumber;
    private String dob;

    private String country;
    private String state;
    private String city;
    private String zipcode;
    
    private LogInDto logindto;

}
