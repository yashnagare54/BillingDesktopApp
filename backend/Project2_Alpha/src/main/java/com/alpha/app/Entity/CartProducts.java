package com.alpha.app.Entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cart_products")

public class CartProducts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long cartProdId;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product products;

	
//	//New Chnage trial purpose 7-8
//	@ManyToOne
//	@JoinColumn(name = "customer_id")
//	private Customer currentCustomer;
	
//	@CreationTimestamp 
//	@Column(name = "cart_created_on")
//	private LocalDate cartCreatedOn;
	
	@ManyToOne
	@JoinColumn(name = "shopCart_id")
	private ShoppingCart shoppingCartObj;
	
	private int quantity;
	
	public CartProducts() {
		// TODO Auto-generated constructor stub
	}

	public CartProducts(Long cartProdId, Product products, ShoppingCart shoppingCartObj, int quantity) {
		super();
		this.cartProdId = cartProdId;
		this.products = products;
		this.shoppingCartObj = shoppingCartObj;
		this.quantity = quantity;
	}

	public Long getCartProdId() {
		return cartProdId;
	}

	public void setCartProdId(Long cartProdId) {
		this.cartProdId = cartProdId;
	}

	public Product getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

	public ShoppingCart getShoppingCartObj() {
		return shoppingCartObj;
	}

	public void setShoppingCartObj(ShoppingCart shoppingCartObj) {
		this.shoppingCartObj = shoppingCartObj;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartProducts [cartProdId=" + cartProdId + ", products=" + products + ", shoppingCartObj="
				+ shoppingCartObj + ", quantity=" + quantity + "]";
	}
	
	
	
	
	
}
