package com.ltifinance.loanapp.customerservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ltifinance.loanapp.customerservice.dto.Role;

@FeignClient(name = "ADMINSERVICE")
@Component
public interface RoleClientService {

	@GetMapping("/api/admincontroller/getrole/{roleName}")
	public Role getRoleName(@PathVariable String roleName);
}
