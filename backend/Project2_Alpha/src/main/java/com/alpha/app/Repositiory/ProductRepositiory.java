package com.alpha.app.Repositiory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alpha.app.Entity.Category;
import com.alpha.app.Entity.Product;

@Repository
public interface ProductRepositiory extends JpaRepository<Product, Long>{

	Optional<Product> findByProdName(String prodName);

	@Query("SELECT p FROM Product p WHERE p.category.cateName =?1")
	List<Product> findByCategory(String cateId);

	

}
