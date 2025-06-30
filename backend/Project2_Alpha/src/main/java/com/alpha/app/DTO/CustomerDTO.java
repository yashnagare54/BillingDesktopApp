package com.alpha.app.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CustomerDTO {

	private Long custId;
	@NotBlank(message = "Please enter name")
	private String custFullName;
	@NotNull
	@Pattern(regexp = "^\\d{10}$",message = "Invalid mobile number entered")
	private String custMobile;
	
	public CustomerDTO() {
		// TODO Auto-generated constructor stub
	}

	public CustomerDTO(Long custId, String custFullName, String custMobile) {
		super();
		this.custId = custId;
		this.custFullName = custFullName;
		this.custMobile = custMobile;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getCustFullName() {
		return custFullName;
	}

	public void setCustFullName(String custFullName) {
		this.custFullName = custFullName;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}
	
	
}
