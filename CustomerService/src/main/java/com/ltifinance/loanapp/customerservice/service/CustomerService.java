package com.ltifinance.loanapp.customerservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ltifinance.loanapp.customerservice.dto.CustomerDto;
import com.ltifinance.loanapp.customerservice.dto.ForgetPassword;
import com.ltifinance.loanapp.customerservice.entity.Customer;
import com.ltifinance.loanapp.customerservice.response.CustomerResponse;

public interface CustomerService {

	public CustomerResponse registerCustomer(Customer customer);

	public ResponseEntity<?> getCustomerByEmail(String email);

	public ResponseEntity<?> getCustomerByUsername(String username);

	public ResponseEntity<?> getCustomerById(int id);

	public ResponseEntity<?> updateCustomerByUsername(String username, CustomerDto dto);

	public ResponseEntity<?> deleteCustomerByEmail(String email);

	public ResponseEntity<?> forgetPassword(ForgetPassword dto);

	public ResponseEntity<?> assignRoleToUser(String username, String roleName);

	public List<String> getAllUsername();

}
