package com.LoginRegister.LoginRegisterServer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity 
public class Users {

	@Id
	private String email;
	
	public Users() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Users(String email, String name, String password) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
	}

	private String name;
	
	private String password;
}
