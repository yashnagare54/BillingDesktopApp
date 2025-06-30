package com.alpha.app.Repositiory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alpha.app.Entity.Category;

@Repository
public interface CategoryRepositiory extends JpaRepository<Category, Long>{

	Optional<Category> findByCateName(String name);

	
	@Query("SELECT c FROM Category c WHERE c.isActive=true")
	List<Category> findByIsActive();

	
}
