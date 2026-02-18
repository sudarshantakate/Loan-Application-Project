package com.ltifinance.loanapp.adminservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ltifinance.loanapp.adminservice.entity.Role;

public interface AdminService {

	public Role createRole(Role role);

	public List<Role> findAll(Role role);

	public ResponseEntity<?> updateRole(String roleName, Role role);

	public ResponseEntity<?> deleterole(String roleName);

}
