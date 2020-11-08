package com.harsha.resource.domain;

public class Address {

	private Integer Id;
	private String addressLine1;
	private String addressLine2;
	private String State;
	private String Country;
	
	
	public Address(Integer id, String addressLine1, String addressLine2, String state, String country) {
		super();
		Id = id;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		State = state;
		Country = country;
	}
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}

	
}
