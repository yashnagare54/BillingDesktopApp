package com.alpha.app.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserSignUpDTO {

	@Email(message = "Please enter valid email")
	@NotNull
	private String userEmail;
	
	@NotEmpty
	private String userPassword;
	
	public UserSignUpDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserSignUpDTO(String userEmail, String userPassword) {
		super();
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "UserSignUpDTO [userEmail=" + userEmail + ", userPassword=" + userPassword + "]";
	}
	
}
