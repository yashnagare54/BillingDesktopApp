package com.alpha.app.DTO;

import java.time.LocalDate;

public class OrderDetailsListDTO {

	private Long orderId;
	private LocalDate orderCreatedOn;
	private double netAmount;
	private String paymentMode;
	
	public OrderDetailsListDTO() {
		// TODO Auto-generated constructor stub
	}

	public OrderDetailsListDTO(Long orderId, LocalDate orderCreatedOn, double netAmount, String paymentMode) {
		super();
		this.orderId = orderId;
		this.orderCreatedOn = orderCreatedOn;
		this.netAmount = netAmount;
		this.paymentMode = paymentMode;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public LocalDate getOrderCreatedOn() {
		return orderCreatedOn;
	}

	public void setOrderCreatedOn(LocalDate orderCreatedOn) {
		this.orderCreatedOn = orderCreatedOn;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	@Override
	public String toString() {
		return "OrderDetailsListDTO [orderId=" + orderId + ", orderCreatedOn=" + orderCreatedOn + ", netAmount="
				+ netAmount + ", paymentMode=" + paymentMode + "]";
	}
	
	
	
}
