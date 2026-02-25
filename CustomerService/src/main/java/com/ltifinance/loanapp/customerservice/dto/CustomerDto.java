package com.ltifinance.loanapp.customerservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ltifinance.loanapp.customerservice.entity.Customer;
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
	
	public CustomerDto convertCustomerToCustomerDto(Customer customer) {
		LogInDto loginDto = null;
		if (customer.getLogin() != null) {
			loginDto = new LogInDto();
			loginDto.setId(customer.getLogin().getId());
			loginDto.setUsername(customer.getLogin().getUsername());
			loginDto.setEmail(customer.getLogin().getEmail());
		}
		CustomerDto dto = new CustomerDto();
		dto.setFname(customer.getFname());
		dto.setLname(customer.getLname());
		dto.setAddress(customer.getAddress());
		dto.setUsername(customer.getUsername());
		dto.setEmail(customer.getEmail());
		dto.setMobileNumber(customer.getMobileNumber());
		dto.setDob(customer.getDob());
		dto.setCountry(customer.getCountry());
		dto.setState(customer.getState());
		dto.setCity(customer.getCity());
		dto.setZipcode(customer.getZipcode());
		dto.setLogindto(loginDto);

		return dto;
	}
}
