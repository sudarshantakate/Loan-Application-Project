package com.ltifinance.loanapp.customerservice.controller;

import org.apache.logging.log4j.LogManager;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ltifinance.loanapp.customerservice.dto.CustomerDto;
import com.ltifinance.loanapp.customerservice.dto.ForgetPassword;
import com.ltifinance.loanapp.customerservice.response.PageResponse;
import com.ltifinance.loanapp.customerservice.service.CustomerService;

@RestController
public class LoginController {

	@Autowired
	private CustomerService service;

	private static final Logger logger = LogManager.getLogger(RegisterController.class);

	@GetMapping(value = "/api/recustomer/getDataByEmailId/{emailId}")
	public ResponseEntity<?> getCustomerByEmail(@PathVariable("emailId") String email) {
		logger.info("In Customer Controller Start");
		ResponseEntity<?> responseEntity = service.getCustomerByEmail(email);
		logger.info("In Customer Controller End");
		return responseEntity;
	}

	@GetMapping(value = "/api/recustomer/getDataByUsername/{username}")
	public ResponseEntity<?> getCustomerByUsername(@PathVariable("username") String username) {
		logger.info("In Customer Controller Start");
		ResponseEntity<?> responseEntity = service.getCustomerByUsername(username);
		logger.info("In Customer Controller End");
		return responseEntity;
	}

	@GetMapping(value = "/api/recustomer/getDataById/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable int id) {
		logger.info("In Customer Controller Start");
		ResponseEntity<?> responseEntity = service.getCustomerById(id);
		logger.info("In Customer Controller End");
		return responseEntity;
	}

	@PostMapping(value = "/api/recustomer/forgetPassword")
	public ResponseEntity<?> forgetPassword(@RequestBody ForgetPassword dto) {
		logger.info("Controller forgot password start");
		ResponseEntity<?> responseEntity = service.forgetPassword(dto);
		logger.info("Controller forgot password end");
		return responseEntity;
	}

	@PostMapping("/api/assign-role/{username}/{roleName}")
	public ResponseEntity<?> assignRole(@PathVariable("username") String username,
			@PathVariable("roleName") String roleName) {
		logger.info("Controller assigne role start");
		return service.assignRoleToUser(username, roleName);
	}

	@GetMapping("/api/getallusername")
	public List<String> getAllUsername() {
		List<String> users = service.getAllUsername();
		return users;
	}

	@GetMapping("/api/pagingCustomers")
	public ResponseEntity<PageResponse<CustomerDto>> getCustomers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		PageResponse<CustomerDto> response = service.getCustomers(page, size);
		return new ResponseEntity<PageResponse<CustomerDto>>(response, HttpStatus.OK);
	}

}
