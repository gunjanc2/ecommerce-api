package com.gc2project.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gc2project.ecommerce.model.Category;
import com.gc2project.ecommerce.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CategoryController {
	
	private CategoryService categoryService;
	
	
	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	//Fetching all categories - GET API
	
	//@GetMapping("/public/categories")
	@RequestMapping(value="/public/categories", method=RequestMethod.GET)
	public ResponseEntity<List<Category>> getAllCategories(){
		//return categoryService.getAllCategories();
		List<Category> categories = categoryService.getAllCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);

	}
	
	//Add new category - POST API
	
	//@PostMapping("/admin/category")
	@RequestMapping(value="/admin/category", method=RequestMethod.POST)
	public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
		categoryService.createCategory(category);
		//return "Category created successfully!";
		return new ResponseEntity<>("Category created successfully", HttpStatus.CREATED);
	}
	
	//Delete a category with categoryId
	
	//@DeleteMapping("/admin/category/{categoryId}")
	@RequestMapping(value="/admin/category/{categoryId}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
		try {
		String status = categoryService.deleteCategory(categoryId);
//		return new ResponseEntity<>(status,HttpStatus.OK);
//		return ResponseEntity.ok(status);
		return ResponseEntity.status(HttpStatus.OK).body(status);
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		}
	}
	
	//Update category with categoryId
	
	//@PutMapping("/admin/category/{categoryId}")
	@RequestMapping(value="/admin/category/{categoryId}", method=RequestMethod.PUT)
	public ResponseEntity<String> updateCategory(@RequestBody Category category,
												@PathVariable Long categoryId){
		try {
			Category updatedCategory = categoryService.updateCategory(category,categoryId);
			return new ResponseEntity<>("Category with Category ID: "+categoryId+" is updated.",HttpStatus.OK);
			
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(),e.getStatusCode());
		}
	}
	
}
