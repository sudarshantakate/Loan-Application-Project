package com.ltifinance.loanapp.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ltifinance.loanapp.adminservice.entity.Role;

@Repository
public interface AdminRepository extends JpaRepository<Role, Integer> {

	Role findByRoleName(String roleName);
}
