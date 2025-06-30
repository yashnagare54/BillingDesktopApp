package com.alpha.app.Repositiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alpha.app.Entity.Customer;

@Repository
public interface CustomerRepositiory extends JpaRepository<Customer, Long> {

}
