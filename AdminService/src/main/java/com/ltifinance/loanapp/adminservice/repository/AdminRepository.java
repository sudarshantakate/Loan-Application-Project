package com.ltifinance.loanapp.adminservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ltifinance.loanapp.adminservice.entity.Role;

@Repository
public interface AdminRepository extends JpaRepository<Role, Integer> {

//	@Query(value = "select role_name from role", nativeQuery = true)
//	List<Role> findAll();

	Role findByRoleName(String roleName);
}
