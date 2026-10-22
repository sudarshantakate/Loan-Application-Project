package com.ltifinance.loanapp.loanservice.security.repository;

import com.ltifinance.loanapp.loanservice.security.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);
}