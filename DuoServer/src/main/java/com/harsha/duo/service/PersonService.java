package com.harsha.duo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harsha.duo.domain.Address;
import com.harsha.duo.domain.Person;

@Service
public class PersonService {

	private static final String RESOURCE_ADDRESS = "http://localhost:9012/resource/address";


	@Autowired
	RestTemplate restTemplate;

	
	ObjectMapper mapper = new ObjectMapper();
	
	public Person getPerson() {
		String addressResponse = restTemplate.getForObject(RESOURCE_ADDRESS,String.class);
		Address address = null;
		try {
			address = mapper.readValue(addressResponse.toString(), Address.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Person person = new Person(1, "FirstName", "LastName", address);
		return person;
	}
	
}
