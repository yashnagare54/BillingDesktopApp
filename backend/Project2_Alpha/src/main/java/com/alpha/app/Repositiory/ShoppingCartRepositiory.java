package com.alpha.app.Repositiory;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alpha.app.Entity.Customer;
import com.alpha.app.Entity.ShoppingCart;

@Repository
public interface ShoppingCartRepositiory extends JpaRepository<ShoppingCart, Long>{

	
	@Query("SELECT s.shopCartId FROM ShoppingCart s WHERE s.customerObj.custId = ?1 AND s.shopCartCreateOn = ?2 ORDER BY s.shopCartId DESC LIMIT 1")
	Long findByCustomerIdAndTodayDate(Long cust, LocalDate now);

}
