package com.ltifinance.loanapp.loanservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ltifinance.loanapp.loanservice.dto.LoanDTO;
import com.ltifinance.loanapp.loanservice.entity.Loan;

public interface LoanService {

	ResponseEntity<?> applyLoan(LoanDTO dto);

	List<Loan> getCustomerLoansById(Long customerId);

	ResponseEntity<?> approveLoanByLoanId(Long loanId);

	ResponseEntity<String> getLoanStatus(Long loanId);

	ResponseEntity<String> rejectLoan(Long loanId);
}
