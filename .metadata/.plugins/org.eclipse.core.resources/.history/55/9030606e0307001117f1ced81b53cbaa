package com.ltifinance.loanapp.customerservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
}
