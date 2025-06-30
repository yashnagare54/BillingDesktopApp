package com.alpha.app.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ProductMaster")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_Id")
	private Long prodId;

	@Column(name = "product_name", nullable = false)
	private String prodName;

	@Column(name = "product_price", nullable = false)
	private double prodPrice;

	@Column(name = "product_image_url")
	private String prodImageUrl;

	@Column(name = "prod_created_date")
	private LocalDate prodCreatedOn;

	@Column(name = "prod_last_update_date")
	private LocalDate prodLastUpdatedOn;
	
	@Column(name = "prod_status")
	private boolean isProdActive;
	
	@ManyToOne
	@JoinColumn(name = "cate_Id")
	private Category category;
	
//	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "products")
//	private List<ShoppingCart> shoppingCartList = new ArrayList<ShoppingCart>();
	
//	@OneToMany(mappedBy = "products",cascade = CascadeType.ALL,orphanRemoval = true)
//	private List<CartProducts> cartProductList = new ArrayList<CartProducts>();

	
	public Product() {
		// TODO Auto-generated constructor stub
		this.isProdActive = true;
	}

	

	public Product(Long prodId, String prodName, double prodPrice, String prodImageUrl, LocalDate prodCreatedOn,
		LocalDate prodLastUpdatedOn,  Category category) {
	super();
	this.prodId = prodId;
	this.prodName = prodName;
	this.prodPrice = prodPrice;
	this.prodImageUrl = prodImageUrl;
	this.prodCreatedOn = prodCreatedOn;
	this.prodLastUpdatedOn = prodLastUpdatedOn;
	this.isProdActive = true;
	this.category = category;
}


	

	public boolean isProdActive() {
		return isProdActive;
	}



	public void setProdActive(boolean isProdActive) {
		this.isProdActive = isProdActive;
	}



	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public double getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(double prodPrice) {
		this.prodPrice = prodPrice;
	}

	public String getProdImageUrl() {
		return prodImageUrl;
	}

	public void setProdImageUrl(String prodImageUrl) {
		this.prodImageUrl = prodImageUrl;
	}

	public LocalDate getProdCreatedOn() {
		return prodCreatedOn;
	}

	public void setProdCreatedOn(LocalDate prodCreatedOn) {
		this.prodCreatedOn = prodCreatedOn;
	}

	public LocalDate getProdLastUpdatedOn() {
		return prodLastUpdatedOn;
	}

	public void setProdLastUpdatedOn(LocalDate prodLastUpdatedOn) {
		this.prodLastUpdatedOn = prodLastUpdatedOn;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	

	@Override
	public String toString() {
		return "Product [prodId=" + prodId + ", prodName=" + prodName + ", prodPrice=" + prodPrice + ", prodImageUrl="
				+ prodImageUrl + ", prodCreatedOn=" + prodCreatedOn + ", prodLastUpdatedOn=" + prodLastUpdatedOn
				+ ", isProdActive=" + isProdActive + ", category=" + category + "]";
	}


	// Helper method to set image path to product
	public String addImageToProduct(String imagePath) {
		this.setProdImageUrl(imagePath);
		return "Image set successfully";
	}
	
	// Helper method to add product in shooping cart
//	public String addProductToShoopingCart(ShoppingCart cart)
//	{
//		this.shoppingCartList.add(cart);
//		return "Product added to cart done";
//	}
	
}
