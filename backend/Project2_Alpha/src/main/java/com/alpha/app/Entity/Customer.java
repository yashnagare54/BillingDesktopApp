package com.alpha.app.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
 

@Entity
@Table(name = "CustomerMaster")


public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_Id")
	private Long custId;

	@Column(name = "full_name", nullable = false)
	private String custFullName;

	@Size(max = 10, min = 10)
	@Column(name = "cust_mobile", nullable = false, unique = true)
	private String custMobile;

	@Column(name = "cust_created_date")
	private LocalDate custCreatedOn;

	//New Change for Cart 
//	@OneToOne()
//	private ShoppingCart shoopingCartId;
	
	
	//New Change trial purpose 7-8
//	@OneToMany(mappedBy = "currentCustomer", cascade = CascadeType.ALL,orphanRemoval = true)
//	private Customer cust;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(Long custId, String custFullName, @Size(max = 10, min = 10) String custMobile,
			LocalDate custCreatedOn) {
		super();
		this.custId = custId;
		this.custFullName = custFullName;
		this.custMobile = custMobile;
		this.custCreatedOn = custCreatedOn;
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

	public LocalDate getCustCreatedOn() {
		return custCreatedOn;
	}

	public void setCustCreatedOn(LocalDate custCreatedOn) {
		this.custCreatedOn = custCreatedOn;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custFullName=" + custFullName + ", custMobile=" + custMobile
				+ ", custCreatedOn=" + custCreatedOn + "]";
	}
 

	 

	
}
