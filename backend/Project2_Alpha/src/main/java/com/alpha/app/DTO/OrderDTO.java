package com.alpha.app.DTO;

import java.util.ArrayList;
import java.util.List;

import com.alpha.app.Entity.Customer;


public class OrderDTO {

	private List<OrderItemsDTO> orderItems= new ArrayList<>();
	private double subTotalAmt;
	private int discountPercentage;
	private double discountAmount;
	private double netAmount;
	private String paymentMode;
	
	private Customer customer;
	
	public OrderDTO() {
		// TODO Auto-generated constructor stub
	}

	
	public OrderDTO(List<OrderItemsDTO> orderItems, double subTotalAmt, int discountPercentage, double discountAmount,
			double netAmount, String paymentMode, Customer customer) {
		super();
		this.orderItems = orderItems;
		this.subTotalAmt = subTotalAmt;
		this.discountPercentage = discountPercentage;
		this.discountAmount = discountAmount;
		this.netAmount = netAmount;
		this.paymentMode = paymentMode;
		this.customer = customer;
	}



	public List<OrderItemsDTO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemsDTO> orderItems) {
		this.orderItems = orderItems;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public String getPaymentMode() {
		return paymentMode;
	}


	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	
	
}
