package com.learning.spring.SpringSecurity.models;

public class User {
	String name;
	String password;
	String emailId;
	String location;

	public User(String name, String password, String emailId, String location) {
		super();
		this.name = name;
		this.password = password;
		this.emailId = emailId;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", emailId=" + emailId + ", location=" + location
				+ "]";
	}

}
