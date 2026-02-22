package com.ltifinance.loanapp.adminservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ltifinance.loanapp.adminservice.entity.Role;
import com.ltifinance.loanapp.adminservice.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository repo;

	@Override
	public Role createRole(Role role) {
		return repo.save(role);
	}

	@Override
	public List<String> findAll() {

		return repo.findAll().stream().map(Role::getRoleName).collect(Collectors.toList());
	}

	@Override
	public ResponseEntity<?> updateRole(String roleName, Role role) {
		Role existingRole = repo.findByRoleName(roleName);
		if (existingRole == null) {
			return new ResponseEntity<>("Role not found", HttpStatus.OK);
		}
		existingRole.setRoleName(role.getRoleName());
		repo.save(existingRole);
		return new ResponseEntity<>("Role updated Successfully!!", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleterole(String roleName) {
		Role existingRole = repo.findByRoleName(roleName);
		if (existingRole == null) {
			return new ResponseEntity<>("Role not found", HttpStatus.OK);
		}
		repo.delete(existingRole);
		return new ResponseEntity<>("Role delete successfully ", HttpStatus.OK);
	}

}
