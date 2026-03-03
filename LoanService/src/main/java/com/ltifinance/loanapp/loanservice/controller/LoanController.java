package com.ltifinance.loanapp.loanservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ltifinance.loanapp.loanservice.dto.LoanDTO;
import com.ltifinance.loanapp.loanservice.entity.Loan;
import com.ltifinance.loanapp.loanservice.service.LoanService;

@RestController
@RequestMapping("loan")
public class LoanController {

	@Autowired
	private LoanService loanService;

	@PostMapping(value = "/api/applyloans")
	public ResponseEntity<?> applyLoan(@RequestBody LoanDTO dto) {
		return loanService.applyLoan(dto);
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/api/getcustomer/{customer_id}")
	public ResponseEntity<?> getCustomerLoanById(@PathVariable("customer_id") Long customerId) {
		List<Loan> loanDeatils = loanService.getCustomerLoansById(customerId);
		if (loanDeatils.isEmpty()) {
			return new ResponseEntity("Loan Details Not Found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(loanDeatils, HttpStatus.FOUND);
	}

	@PutMapping(value = "/api/approveloan/{loanid}")
	public ResponseEntity<?> approveLoan(@PathVariable("loanid") Long loanId) {
		ResponseEntity<?> response = loanService.approveLoanByLoanId(loanId);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@GetMapping(value = "/api/getloanstatus/{loanid}")
	public ResponseEntity<String> getLoanStatus(@PathVariable("loanid") Long loanId) {
		return loanService.getLoanStatus(loanId);
	}
	
	@PutMapping(value = "/api/rejectloan/{loanid}")
	public ResponseEntity<String> rejectLoan(@PathVariable("loanid") Long loanId){
		return loanService.rejectLoan(loanId);
	}
}
