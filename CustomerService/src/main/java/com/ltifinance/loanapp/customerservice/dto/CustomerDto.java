package com.ltifinance.loanapp.customerservice.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
