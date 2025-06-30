package com.alpha.app.DTO;

import com.alpha.app.Entity.Product;


public class OrderItemsDTO {

	private Product product;
	private int quantity;
	
	public OrderItemsDTO() {
		// TODO Auto-generated constructor stub
	}

	public OrderItemsDTO(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
