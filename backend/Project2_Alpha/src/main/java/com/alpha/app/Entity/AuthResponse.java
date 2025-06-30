package com.alpha.app.Entity;

public class AuthResponse {

	private String message;
	private String jwtToken;
	private Object user;
	
	public AuthResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public AuthResponse(String message, String jwtToken, Object user) {
		super();
		this.message = message;
		this.jwtToken = jwtToken;
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public Object getUser() {
		return user;
	}

	public void setUser(Object user) {
		this.user = user;
	}
	
	
}
