package com.ltifinance.loanapp.loanservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ltifinance.loanapp.loanservice.constant.EnumData;
import com.ltifinance.loanapp.loanservice.dto.LoanDTO;
import com.ltifinance.loanapp.loanservice.entity.Loan;
import com.ltifinance.loanapp.loanservice.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanRepository loanRepository;

	@Override
	public ResponseEntity<?> applyLoan(LoanDTO dto) {
		if (dto.getLoanAmount() == null || dto.getLoanAmount() <= 0) {
			return new ResponseEntity<>("Invalid Loan Amount", HttpStatus.BAD_REQUEST);
		}

		if (dto.getTenureMonths() == null || dto.getTenureMonths() <= 0) {
			return new ResponseEntity<>("Invalid Tenure Months", HttpStatus.BAD_REQUEST);
		}

		if (dto.getCustomerId() == null) {
			return new ResponseEntity<>("Customer Id is required", HttpStatus.BAD_REQUEST);
		}

		Loan loan = new Loan();
		loan.setCustomerId(dto.getCustomerId());
		loan.setLoanAmount(dto.getLoanAmount());
		loan.setTenureMonths(dto.getTenureMonths());
		loan.setLoanType(dto.getLoanType());
		loan.setStatus(EnumData.PENDING.getValue());
		loan.setApplicationDate(LocalDate.now());
		loan.setDeleted(false);

		// Calculate EMI
		Double annualInterestRate = 8.5;
		Double emiAmount = calculateEmi(dto.getLoanAmount(), dto.getTenureMonths(), annualInterestRate);
		loan.setEmiAmount(emiAmount);
		loanRepository.save(loan);

		return new ResponseEntity<>("Loan Applied Successfully", HttpStatus.CREATED);
	}

	private Double calculateEmi(Double loanAmount, Integer tenureMonths, Double annualInterestRate) {
		if (loanAmount == null || tenureMonths == null || annualInterestRate == null) {
			return 0.0;
		}
		double monthlyRate = annualInterestRate / (12 * 100);
		int months = tenureMonths;

		double emi = (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, months))
				/ (Math.pow(1 + monthlyRate, months) - 1);

		return emi;
	}

	@Override
	public List<Loan> getCustomerLoansById(Long customerId) {
		return loanRepository.findByCustomerId(customerId);
	}

	@Override
	public ResponseEntity<?> approveLoanByLoanId(Long loanId) {
		Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan Details Not Found"));
		if (loan.getStatus() == EnumData.APPROVED.getValue()) {
			return new ResponseEntity("Loan is already approved", HttpStatus.CONFLICT);
		}
		loan.setStatus(EnumData.APPROVED.getValue());
		loan.setApprovedDate(LocalDate.now());
		loanRepository.save(loan);
		return new ResponseEntity("Loan approved successfully", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getLoanStatus(Long loanId) {
		Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
		EnumData status = EnumData.fromValue(loan.getStatus());
		return ResponseEntity.ok(status.name());
	}

	@Override
	public ResponseEntity<String> rejectLoan(Long loanId) {
		Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));

		// If already rejected
		if (loan.getStatus().equals(EnumData.REJECTED.getValue())) {
			return new ResponseEntity<>("Loan already rejected", HttpStatus.CONFLICT);
		}

		// If already disbursed, cannot reject
		if (loan.getStatus().equals(EnumData.DISBURSED.getValue())) {
			return new ResponseEntity<>("Cannot reject disbursed loan", HttpStatus.CONFLICT);
		}

		loan.setStatus(EnumData.REJECTED.getValue());
		loanRepository.save(loan);

		return new ResponseEntity<>("Loan rejected successfully", HttpStatus.OK);
	}
}
