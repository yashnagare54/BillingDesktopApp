package com.alpha.app.Repositiory;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alpha.app.DTO.OrderItemsDTO;
import com.alpha.app.Entity.CartProducts;
import com.alpha.app.Entity.Customer;
import com.alpha.app.Entity.Product;
import com.alpha.app.Entity.ShoppingCart;

@Repository
public interface CartProductsRepositiory extends JpaRepository<CartProducts, Long>{

//	@Query("SELECT o FROM CartProducts o WHERE o.shoppingCartObj.shopCartId =?1")
//	List<CartProducts> findAllByShoppingCartObj(Long shopCart);
	
	@Query("SELECT new com.alpha.app.DTO.OrderItemsDTO(o.products, o.quantity) FROM CartProducts o WHERE o.shoppingCartObj.shopCartId =?1")
	List<OrderItemsDTO> findAllByShoppingCartObj(Long shopCart);

	@Query("SELECT p.products FROM CartProducts p GROUP BY p.products.prodId ORDER BY COUNT(p.products.prodId) DESC LIMIT 5")
	List<Product> findMostSelling5Products();

	@Query("SELECT cp.cartProdId FROM CartProducts cp WHERE cp.products.prodId = ?1")
	List<Long> findByProduct(Long long1);



//	@Modifying
//	@Query("DELETE FROM CartProducts c WHERE c.products.prodId = ?1 AND c.currentCustomer.custId = ?2 AND c.cartCreatedOn = ?3")
//	void deleteByProductsAndCurrentCustomerAndCartCreatedOn(Long prodId, Long custId, LocalDate localDate);

}
