package com.gc2project.ecommerce.controller;

import com.gc2project.ecommerce.model.Category;
import com.gc2project.ecommerce.service.CategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
		
//	public CategoryController(CategoryService categoryService) {
//		super();
//		this.categoryService = categoryService;
//	}

	@GetMapping("/api/public/categories")
	public List<Category> getAllCategories(){
		return categoryService.getAllCategories();
	}

	@PostMapping("api/admin/category")
	public String createCategory(@RequestBody Category category) {
		categoryService.createCategory(category);
		return "Category added successfully!";
	}
	
	@DeleteMapping("/api/admin/category/{categoryId}")
	public String deleteCategory(@PathVariable Long categoryId) {
		String status = categoryService.deleteCategory(categoryId);
		return status;
	}
}
