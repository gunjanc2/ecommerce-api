package com.gc2project.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gc2project.ecommerce.exceptions.APIException;
import com.gc2project.ecommerce.exceptions.ResourceNotFoundException;
import com.gc2project.ecommerce.model.Category;
import com.gc2project.ecommerce.repositories.CategoryRepository;


@Service
public class CategoryServiceImpl implements CategoryService{
	
	//Add unimplemented methods
	
	//private List<Category> categories = new ArrayList<>();
	
	private Long nextId = 1L;
	
	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public List<Category> getAllCategories() {
		return categoryRepo.findAll();
	}

	@Override
	public void createCategory(Category category) {
		Category savedCategory = categoryRepo.findByCategoryName(category.getCategoryName());
		if (savedCategory != null) {
			throw new APIException("Category with category name: "+category.getCategoryName()+" already exists!");
		}
		category.setCategoryId(nextId++);
		categoryRepo.save(category);
		
	}

	@Override
	public String deleteCategory(Long categoryId) {
		
		Category deleteCategory = categoryRepo.findById(categoryId)
				//.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found Exception"));
				.orElseThrow(() -> new ResourceNotFoundException("Category","category ID",categoryId));
		
		categoryRepo.delete(deleteCategory);
		
		return "Category with category ID: "+categoryId+" deleted successfully!";
	}

	@Override
	public Category updateCategory(Category category, Long categoryId) {
		
		Category savedCategory = categoryRepo.findById(categoryId)
				//.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found Exception"));
		.orElseThrow(() -> new ResourceNotFoundException("Category","category ID",categoryId));
		
		//If category exists, it will be assigned to savedCategory

		category.setCategoryId(categoryId);
		savedCategory=categoryRepo.save(category);
		
		return savedCategory;
	}

}
