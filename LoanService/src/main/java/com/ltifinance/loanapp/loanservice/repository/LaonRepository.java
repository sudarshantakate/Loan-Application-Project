package com.ltifinance.loanapp.loanservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ltifinance.loanapp.loanservice.entity.Loan;

public interface LaonRepository extends JpaRepository<Loan, Long>{

}
