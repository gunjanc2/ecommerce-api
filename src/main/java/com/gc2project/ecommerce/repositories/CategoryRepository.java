package com.gc2project.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gc2project.ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	//No implementation required
	//Spring Data JPA will automatically generate the implementations at runtime
	//We can use all CRUD operations on the Categories table

}
