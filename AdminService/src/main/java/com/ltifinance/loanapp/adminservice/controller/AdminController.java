package com.ltifinance.loanapp.adminservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ltifinance.loanapp.adminservice.entity.Role;
import com.ltifinance.loanapp.adminservice.repository.AdminRepository;
import com.ltifinance.loanapp.adminservice.service.AdminService;

@RestController
public class AdminController {

	@Autowired
	private AdminService service;
	
	@Autowired
	private AdminRepository adminRepository;

	@PostMapping(value = "/api/admincontroller/createrole")
	public Role createRole(@RequestBody Role role) {
		return service.createRole(role);
	}

	@GetMapping(value = "/api/admincontroller/findall")
	public List<String> FindAll() {
		return service.findAll();
	}

	@PutMapping(value = "/api/admincontroller/updateRole/{rolename}")
	public ResponseEntity<?> updateRole(@PathVariable("rolename") String roleName, @RequestBody Role role) {
		return service.updateRole(roleName,role);
	}
	
	@DeleteMapping(value ="/api/admincontroller/deleteRole/{rolename}" )
	public ResponseEntity<?> deleteRole(@PathVariable("rolename") String roleName){
		return service.deleterole(roleName);
	}
	
	@GetMapping("/api/admincontroller/getrole/{roleName}")
	public Role getRole(@PathVariable String roleName) {
	    return adminRepository.findByRoleName(roleName);
	}
}
