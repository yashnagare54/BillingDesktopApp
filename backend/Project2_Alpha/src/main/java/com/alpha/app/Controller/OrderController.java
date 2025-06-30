package com.alpha.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.app.DTO.OrderDTO;
import com.alpha.app.Service.IOrderService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private IOrderService orderService;

	@PostMapping("/place-order")
	ResponseEntity<?> placedOrder(@RequestBody OrderDTO orderDetails)
	{
		return orderService.placeOrderDetails(orderDetails);
	}
	
	//If customer required his todays bill or drop his bill, by this method he can get latest bill
	@GetMapping("/get-customer-bill/{custId}")
	ResponseEntity<?> getCustomerLatestBill(@PathVariable Long custId)
	{
		return orderService.getCustLatestBill(custId);
	}
	
	
	//Get Top 5 most selling Products OR Top fav customer dishes
	@GetMapping("/best-selling")
	ResponseEntity<?> getTopMostSellingProducts()
	{
		return orderService.getListOfTop5MostSellingProducts();
	}
	
	@GetMapping("/get-all")
	ResponseEntity<?> getAllOrderList()
	{
		return orderService.getAllOrders();
	}
}
