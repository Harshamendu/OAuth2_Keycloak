package com.harsha.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harsha.client.domain.Person;

@Service
public class ClientService {

	
	private static final String GET_DUO_PERSON = "http://localhost:9011/duo/getDuoPerson";

	@Autowired
	RestTemplate restTemplate;

	ObjectMapper mapper = new ObjectMapper();
	
	public Person getClientPerson() {
		
		String personResponse = restTemplate.getForObject(GET_DUO_PERSON,String.class);
		Person person = null;
		try {
			person = mapper.readValue(personResponse.toString(), Person.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return person;
	}
}
