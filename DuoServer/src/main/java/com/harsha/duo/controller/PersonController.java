package com.harsha.duo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harsha.duo.domain.Person;
import com.harsha.duo.service.PersonService;

@RestController
@RequestMapping("/duo")
public class PersonController {

	@Autowired
	PersonService personService;
	
	@GetMapping("/getDuoPerson")
	public Person getPerson() {
		return personService.getPerson();
	}
}
