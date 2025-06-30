package com.alpha.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.app.DTO.CustomerDTO;
import com.alpha.app.Entity.Customer;
import com.alpha.app.Service.ICustomerService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private ICustomerService custService;
	
	
	@GetMapping("/allcustomers")
	List<Customer> fetchAllCustomerList()
	{
		return custService.getAllCustomers();
	}
	
	@PostMapping("/register_customer")
	ResponseEntity<?> registerNewCustomer(@RequestBody CustomerDTO addCust)
	{
		return custService.addNewCustomer(addCust);
	}
}
