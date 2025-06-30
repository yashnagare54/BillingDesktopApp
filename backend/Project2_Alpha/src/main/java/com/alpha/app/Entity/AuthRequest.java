package com.alpha.app.Entity;

import jakarta.validation.constraints.NotBlank;

public class AuthRequest {

	@NotBlank(message = "Email can't be blank or null")
	private String name;
	
	@NotBlank(message = "password can't be blank or null")
	private String password;
	
	public AuthRequest() {
		// TODO Auto-generated constructor stub
	}

	public AuthRequest(@NotBlank(message = "Email can't be blank or null") String name,
			@NotBlank(message = "password can't be blank or null") String password) {
		super();
		this.name = name;
		this.password = password;
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

	@Override
	public String toString() {
		return "AuthRequest [name=" + name + ", password=" + password + "]";
	}
	
	

	
}
