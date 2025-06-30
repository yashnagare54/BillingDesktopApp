package com.alpha.app.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="shoppingcart")
@ToString
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shopCartId")
	private Long shopCartId;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customerObj;
	
	@CreationTimestamp 
	@Column(name = "cart_created_on")
	private LocalDate shopCartCreateOn;
		
	
//	@OneToMany
//	@JoinColumn(name = "shop_cart_id",referencedColumnName = "shopCartId")
//	List<CartProducts> cartProductsList = new ArrayList<>();
	
	@OneToMany(mappedBy = "shoppingCartObj",cascade = CascadeType.ALL)
	private List<CartProducts> cartProductsList = new ArrayList<>();
	
	public ShoppingCart() {
		// TODO Auto-generated constructor stub
	}

	public ShoppingCart(Long shopCartId, Customer customerObj, LocalDate shopCartCreateOn,
			List<CartProducts> cartProductsList) {
		super();
		this.shopCartId = shopCartId;
		this.customerObj = customerObj;
		this.shopCartCreateOn = shopCartCreateOn;
		this.cartProductsList = cartProductsList;
	}

	public Long getShopCartId() {
		return shopCartId;
	}

	public void setShopCartId(Long shopCartId) {
		this.shopCartId = shopCartId;
	}

	public Customer getCustomerObj() {
		return customerObj;
	}

	public void setCustomerObj(Customer customerObj) {
		this.customerObj = customerObj;
	}

	public LocalDate getShopCartCreateOn() {
		return shopCartCreateOn;
	}

	public void setShopCartCreateOn(LocalDate shopCartCreateOn) {
		this.shopCartCreateOn = shopCartCreateOn;
	}

	public List<CartProducts> getCartProductsList() {
		return cartProductsList;
	}

	public void setCartProductsList(List<CartProducts> cartProductsList) {
		this.cartProductsList = cartProductsList;
	}
	
}
