package com.alpha.app.DTO;
public class AddProductDTO {

	private Long prodId;
	
	private Long custId;
	
	private int quantity;
	
	public AddProductDTO() {
		// TODO Auto-generated constructor stub
	}

	public AddProductDTO(Long prodId, Long custId, int quantity) {
		super();
		this.prodId = prodId;
		this.custId = custId;
		this.quantity = quantity;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
