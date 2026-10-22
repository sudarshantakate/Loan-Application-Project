package com.ltifinance.loanapp.loanservice;


import com.ltifinance.loanapp.loanservice.constant.EnumData;
import com.ltifinance.loanapp.loanservice.entity.Loan;
import com.ltifinance.loanapp.loanservice.repository.LoanRepository;
import com.ltifinance.loanapp.loanservice.service.LoanServiceImpl;
import com.ltifinance.loanapp.loanservice.dto.LoanDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LoanServiceImplTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    @Test
    void applyLoanShouldRejectZeroLoanAmount() {
        LoanDTO dto = new LoanDTO();
        dto.setCustomerId(1L);
        dto.setLoanAmount(0.0);
        dto.setTenureMonths(12);
        dto.setLoanType("PERSONAL");

        ResponseEntity<?> response = loanService.applyLoan(dto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid Loan Amount", response.getBody());
    }


    @Test
    void applyLoanShouldSaveLoanWithPendingStatus() {
        LoanDTO dto = new LoanDTO();
        dto.setCustomerId(1L);
        dto.setLoanAmount(50000.0);
        dto.setTenureMonths(12);
        dto.setLoanType("PERSONAL");

        loanService.applyLoan(dto);

        org.mockito.ArgumentCaptor<Loan> captor =
                org.mockito.ArgumentCaptor.forClass(Loan.class);

        org.mockito.Mockito.verify(loanRepository)
                .save(captor.capture());

        Loan savedLoan = captor.getValue();

        assertEquals(EnumData.PENDING.getValue(), savedLoan.getStatus());
    }

    @Test
    void applyLoanShouldRejectInvalidTenure() {
        LoanDTO dto = new LoanDTO();
        dto.setCustomerId(1L);
        dto.setLoanAmount(50000.0);
        dto.setTenureMonths(0);
        dto.setLoanType("PERSONAL");

        ResponseEntity<?> response = loanService.applyLoan(dto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
