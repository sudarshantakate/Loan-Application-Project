package com.ltifinance.loanapp.customerservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ltifinance.loanapp.customerservice.entity.LogIn;

@Repository
public interface LoginRepository extends JpaRepository<LogIn, Integer> {

	LogIn findById(int id);

	Optional<LogIn> findByUsername(String username);

	@Query(value = "select username from log_in", nativeQuery = true)
	List<String> findByAllUsername();
}
