package com.alpha.app.Entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ordermaster")

public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_Id")
	private Long orderId;
	
	@ManyToOne
	@JoinColumn(name = "shopcart_id")
	private ShoppingCart shoppingCart;
	
	@ManyToOne
	@JoinColumn(name ="customer_id")
	private Customer customerObj;
	
	@Column(name="sub_total")
	private double subTotalAmt;
	
	@Column(name = "discount_percentage")
	private int discountPercentage;
	
	@Column(name="discount_amount")
	private double discountAmount;
	

	@Column(name="net_amount")
	private double netAmount;
	
	@Column(name="payment_mtd")
	private String paymentMode;
	
	@CreationTimestamp 
	@Column(name = "order_created_date")
	private LocalDate orderCreatedOn;

	public Order() {
		// TODO Auto-generated constructor stub
	}


	public Order(Long orderId, ShoppingCart shoppingCart, Customer customerObj, double subTotalAmt,
			int discountPercentage, double discountAmount, double netAmount, String paymentMode,
			LocalDate orderCreatedOn) {
		super();
		this.orderId = orderId;
		this.shoppingCart = shoppingCart;
		this.customerObj = customerObj;
		this.subTotalAmt = subTotalAmt;
		this.discountPercentage = discountPercentage;
		this.discountAmount = discountAmount;
		this.netAmount = netAmount;
		this.paymentMode = paymentMode;
		this.orderCreatedOn = orderCreatedOn;
	}



	public String getPaymentMode() {
		return paymentMode;
	}


	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}


	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public Customer getCustomerObj() {
		return customerObj;
	}

	public void setCustomerObj(Customer customerObj) {
		this.customerObj = customerObj;
	}

	public double getSubTotalAmt() {
		return subTotalAmt;
	}

	public void setSubTotalAmt(double subTotalAmt) {
		this.subTotalAmt = subTotalAmt;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public LocalDate getOrderCreatedOn() {
		return orderCreatedOn;
	}

	public void setOrderCreatedOn(LocalDate orderCreatedOn) {
		this.orderCreatedOn = orderCreatedOn;
	}
	
	
}
