package com.ltifinance.loanapp.loanservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ltifinance.loanapp.loanservice.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{
	 List<Loan> findByCustomerId(Long customerId);

}
