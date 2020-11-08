package com.harsha.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsha.client.domain.Person;
import com.harsha.client.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ClientService clientService;
	
	@GetMapping("/getClientPerson")
	public Person getPerson() {
		return clientService.getClientPerson();
	}
}
