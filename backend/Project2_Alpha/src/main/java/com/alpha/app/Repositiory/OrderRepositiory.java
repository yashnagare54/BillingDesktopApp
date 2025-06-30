package com.alpha.app.Repositiory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alpha.app.DTO.OrderDetailsListDTO;
import com.alpha.app.Entity.Order;

@Repository
public interface OrderRepositiory extends JpaRepository<Order, Long>{

	@Query("SELECT new com.alpha.app.DTO.OrderDetailsListDTO( o.orderId, o.orderCreatedOn, o.netAmount, o.paymentMode) FROM Order o")
	List<OrderDetailsListDTO> findAllOrdersDetails();

}
