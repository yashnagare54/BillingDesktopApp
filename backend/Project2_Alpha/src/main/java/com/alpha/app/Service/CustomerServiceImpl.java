package com.alpha.app.Service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.app.DTO.CustomerDTO;
import com.alpha.app.Entity.Customer;
import com.alpha.app.Repositiory.CustomerRepositiory;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepositiory custRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<Customer> getAllCustomers() {
		
		return custRepo.findAll();
	}

	@Override
	public ResponseEntity<?> addNewCustomer(CustomerDTO addCust) {
		// TODO Auto-generated method stub
		Customer cust = mapper.map(addCust, Customer.class);
		cust.setCustCreatedOn(LocalDate.now());
		custRepo.save(cust);
		return new ResponseEntity<>(cust, HttpStatus.CREATED);
	}
}
