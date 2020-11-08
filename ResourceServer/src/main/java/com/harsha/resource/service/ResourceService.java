package com.harsha.resource.service;

import org.springframework.stereotype.Service;

import com.harsha.resource.domain.Address;

@Service
public class ResourceService {

	
	public Address getAddressById() {
		Address add =  new Address(1,"Address Line 1","Address Line 2","Address State","Address Country");
		return add;
	}
	
}
