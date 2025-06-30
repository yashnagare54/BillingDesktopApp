package com.alpha.app.DTO;

 


public class RemoveProductFromCartDTO {

	private Long prodId;
	
	private Long custId;
 
	
	public RemoveProductFromCartDTO() {
		// TODO Auto-generated constructor stub
	}

 
	public RemoveProductFromCartDTO(Long prodId, Long custId) {
		super();
		this.prodId = prodId;
		this.custId = custId;
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
	
	
}
