package com.alpha.app.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.alpha.app.DTO.CustomerDTO;
import com.alpha.app.Entity.Customer;

public interface ICustomerService {

	List<Customer> getAllCustomers();

	ResponseEntity<?> addNewCustomer(CustomerDTO addCust);

}
