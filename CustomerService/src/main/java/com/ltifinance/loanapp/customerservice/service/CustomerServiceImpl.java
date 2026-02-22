package com.ltifinance.loanapp.customerservice.service;

import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

import com.ltifinance.loanapp.customerservice.client.RoleClientService;
import com.ltifinance.loanapp.customerservice.constant.EnumData;
import com.ltifinance.loanapp.customerservice.dto.CustomerDto;
import com.ltifinance.loanapp.customerservice.dto.ForgetPassword;
import com.ltifinance.loanapp.customerservice.dto.Role;
import com.ltifinance.loanapp.customerservice.entity.Customer;
import com.ltifinance.loanapp.customerservice.entity.LogIn;
import com.ltifinance.loanapp.customerservice.exception.ResourceNotFoundException;
import com.ltifinance.loanapp.customerservice.repository.CustomerRepository;
import com.ltifinance.loanapp.customerservice.repository.LoginRepository;
import com.ltifinance.loanapp.customerservice.response.CustomerResponse;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerrepository;

	@Autowired
	private LoginRepository loginrepo;

	@Autowired
	private ModelMapper modelMapper;

//	@Autowired
//	private RestTemplate restTemplate;

	@Autowired
	private RoleClientService roleClientService;

	private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

	@Override
	public CustomerResponse registerCustomer(Customer customer) {
		logger.info("In Service Layer Start");
		CustomerResponse customerresponse = new CustomerResponse();
		// set date here
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-YYYY");
		String strDate = dateformat.format(date);
		customer.setCreatedDate(strDate);
		// Set Status
		boolean flag = false;
		EnumData status = EnumData.ACTIVE;
		if ("A".equals(status.getValue())) {
			flag = true;
		}
		customer.setStatus(flag);
		customer.setCreatedBy(customer.getUsername());
		boolean isExist = getAlreadyRegisterdEmployeeData(customer.getEmail());
		if (isExist) {
			customerresponse.setUsername(customer.getEmail());
			customerresponse.setMessage("Customer Email Already Exist.");
			return customerresponse;
		}
		LogIn login = new LogIn();
		login.setUsername(customer.getUsername());
		login.setEmail(customer.getEmail());
		login.setPassword(customer.getPassword());
		login.setCustomer(customer);
		customer.setLogin(login);
		Customer customer1 = customerrepository.save(customer);
		if (customer1 != null) {
			customerresponse.setUsername(customer1.getUsername());
			customerresponse.setMessage("Customer profile created successfully!!");
		} else {
			customerresponse.setMessage("Something wents wrong profile not created!!");
		}
		logger.info("In Service layer End");
		return customerresponse;
	}

	private boolean getAlreadyRegisterdEmployeeData(String email) {
		Customer customer = customerrepository.findByEmail(email);
		if (customer != null) {
			return true;
		}
		return false;
	}

	@Override
	public ResponseEntity<?> getCustomerByEmail(String email) {
		logger.info("In customer impl getting by email start");
		Customer customer = customerrepository.findByEmail(email);
		if (customer != null) {
//			CustomerDto dto = modelMapper.map(customer, CustomerDto.class);
//			LogInDto loginDto = LogInDto.builder().username(customer.getUsername())
//					.email(customer.getEmail()).build();
//			CustomerDto dto = CustomerDto.builder().fname(customer.getFname()).lname(customer.getLname())
//					.address(customer.getAddress()).username(customer.getUsername()).email(customer.getEmail())
//					.mobileNumber(customer.getMobileNumber()).dob(customer.getDob()).country(customer.getCountry())
//					.state(customer.getState()).city(customer.getCity()).zipcode(customer.getZipcode())
//					.logindto(loginDto).build();
			CustomerDto dto2 = customer.convertCustomerToCustomerDto(customer);
			logger.info("Customer found with email: " + email);
			return new ResponseEntity<CustomerDto>(dto2, HttpStatus.OK);
		}
		CustomerResponse customerResponse = new CustomerResponse();
		customerResponse.setUsername(email);
		customerResponse.setMessage("Invalid Email Id please enter valid Email id");
		logger.info("Customer not found with email: " + email);
		logger.info("In customer impl getting by email end");
		return new ResponseEntity<CustomerResponse>(customerResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getCustomerByUsername(String username) {
		logger.info("In customer impl getting by username start");
		Customer customer = customerrepository.findByUsername(username);
		if (customer != null) {
			CustomerDto dto = modelMapper.map(customer, CustomerDto.class);
			logger.info("Customer found with username: " + username);
			return new ResponseEntity<CustomerDto>(dto, HttpStatus.OK);
		}
		CustomerResponse response = new CustomerResponse();
		response.setUsername(username);
		response.setMessage("Invalid username");
		logger.info("Customer not found with username: " + username);
		return new ResponseEntity<CustomerResponse>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getCustomerById(int id) {
		logger.info("In customer impl getting by id start");
		Customer customer = customerrepository.findById(id);
		if (customer != null) {
			CustomerDto dto = modelMapper.map(customer, CustomerDto.class);
			logger.info("Customer found with id: " + id);
			return new ResponseEntity<CustomerDto>(dto, HttpStatus.OK);
		}
		CustomerResponse response = new CustomerResponse();
		response.setMessage("Customer not found with id: " + id);
		logger.info("Customer not found with id: " + id);
		return new ResponseEntity<CustomerResponse>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> updateCustomerByUsername(String username, CustomerDto dto) {
		logger.info("Update customer start");
		Customer customer = customerrepository.findByUsername(username);
		if (customer == null) {
			return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
		}
		modelMapper.map(dto, customer);
		customer.setUpdatedBy(username);
		customer.setUpdateDate(new java.util.Date().toString());
		customerrepository.save(customer);
		logger.info("Customer updated successfully");
		return new ResponseEntity<>("Customer updated successfully", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteCustomerByEmail(String email) {
		logger.info("In Customer Service Delete Customer By Email Method");
		Customer customer = customerrepository.findByEmail(email);
		boolean flag = true;
		if (customer != null) {
			if (customer.isStatus()) {
				EnumData status = EnumData.NONACTIVE;
				if ("N".equals(status.getValue())) {
					flag = false;
				}
				customer.setStatus(flag);
				customer.setUpdatedBy(customer.getUsername());
				customer.setUpdateDate(new java.util.Date().toString());
				customerrepository.save(customer);
				return new ResponseEntity<Customer>(customer, HttpStatus.OK);
			} else {
				CustomerResponse response = new CustomerResponse();
				response.setUsername(email);
				response.setMessage("Customer Status is already NonActive Cannot proceed.");
				return new ResponseEntity<CustomerResponse>(response, HttpStatus.OK);
			}
		}
		CustomerResponse response = new CustomerResponse();
		response.setUsername(email);
		response.setMessage("Customer not found with this email");
		return new ResponseEntity<CustomerResponse>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> forgetPassword(ForgetPassword dto) {
		logger.info("Forgot password process start");
		// check password match
		if (!dto.getNewpassword().equals(dto.getConfirmPassword())) {
			CustomerResponse response = new CustomerResponse();
			response.setUsername(dto.getUsername());
			response.setMessage("New password and confirm password do not match");
			return new ResponseEntity<CustomerResponse>(response, HttpStatus.BAD_REQUEST);
		}
		// verify username + email
		Customer customer = customerrepository.findByUsernameAndEmail(dto.getUsername(), dto.getEmail());
		if (customer != null) {
			customer.setPassword(dto.getNewpassword());
			customer.setUpdatedBy(dto.getUsername());
			customer.setUpdateDate(new java.util.Date().toString());
			customerrepository.save(customer);
			logger.info("Password updated successfully");
			return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
		}
		CustomerResponse response = new CustomerResponse();
		response.setUsername(dto.getUsername());
		response.setMessage("Invalid username or email");
		return new ResponseEntity<CustomerResponse>(response, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<?> assignRoleToUser(String username, String roleName) {
		LogIn login = loginrepo.findByUsername(username);
		if (login == null) {
			return new ResponseEntity<>("User Not Exist!!", HttpStatus.NOT_FOUND);
		}
		//String url = "http://localhost:3002/api/admincontroller/getrole/" + roleName;
		//Role role = restTemplate.getForObject(url, Role.class);
		Role role = roleClientService.getRoleName(roleName);
		if (role == null) {
		    throw new ResourceNotFoundException("Role not found with name: " + roleName);
		}
		login.setRoleName(role.getRoleName());
		loginrepo.save(login);
		return new ResponseEntity<>(login, HttpStatus.OK);
	}

	@Override
	public List<String> getAllUsername() {
		List<String> username = loginrepo.findByAllUsername();
		return username;
	}
}
