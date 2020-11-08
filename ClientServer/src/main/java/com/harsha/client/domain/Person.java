package com.harsha.client.domain;

public class Person {

	private Integer Id;
	private String firstName;
	private String LastName;
	private Address address;

	public Person() {
		super();
	}

	public Person(Integer id, String firstName, String lastName, Address address) {
		super();
		Id = id;
		this.firstName = firstName;
		LastName = lastName;
		this.address = address;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
