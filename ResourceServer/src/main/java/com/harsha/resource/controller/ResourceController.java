package com.harsha.resource.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsha.resource.domain.Address;
import com.harsha.resource.service.ResourceService;

@RestController
public class ResourceController {

	@Autowired
	ResourceService resourceService;
	
	@GetMapping("/address")
	public Address getAddress(Principal principal) {
		return resourceService.getAddressById();
	}
}
