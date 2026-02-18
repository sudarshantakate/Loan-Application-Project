package com.ltifinance.loanapp.customerservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ltifinance.loanapp.customerservice.dto.CustomerDto;
import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String fname;

	@Column(nullable = false)
	private String lname;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String email;

	private String mobileNumber;

	private String dob;

	private String createdBy;

	private String createdDate;

	private String updatedBy;

	private String updateDate;

	private String country;

	private String state;

	private String city;

	private String zipcode;

	private boolean status;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private LogIn login;

	public CustomerDto convertCustomerToCustomerDto(Customer customer) {
		return CustomerDto.builder().fname(customer.getFname()).lname(customer.getLname())
				.address(customer.getAddress()).username(customer.getUsername()).email(customer.getEmail())
				.mobileNumber(customer.getMobileNumber()).dob(customer.getDob()).country(customer.getCountry())
				.state(customer.getState()).city(customer.getCity()).zipcode(customer.getZipcode())
				.logindto(customer.getLogin().convertLoginToLogindto(getLogin())).build();
	}
}
