package com.gc2project.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gc2project.ecommerce.model.Category;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	//Add unimplemented methods
	
	private List<Category> categories = new ArrayList<>();
	
	private Long nextId = 1L;

	@Override
	public List<Category> getAllCategories() {
		return categories;
	}

	@Override
	public void createCategory(Category category) {
		category.setCategoryId(nextId++);
		categories.add(category);
		
	}

	@Override
	public String deleteCategory(Long categoryId) {
		Category category = categories.stream()
				.filter(c -> c.getCategoryId().equals(categoryId))
				.findFirst()
//				.get()
//				.orElse(null);
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found Exception"));
		
//		if(category == null)
//			return "Category not found!";
		
		categories.remove(category);
		
		return "Category with category ID: "+categoryId+" deleted successfully!";
	}

	@Override
	public Category updateCategory(Category category, Long categoryId) {
		
		Optional<Category> optCategory = categories.stream()
				.filter(c -> c.getCategoryId().equals(categoryId))
				.findFirst();
		
		//We can make use of .get() as well above but if the category is not found, we will get an error
		//To avoid exception, we applied below if block
		
		if(optCategory.isPresent()) {
			Category existingCategory = optCategory.get();
			existingCategory.setCategoryName(category.getCategoryName());
			return existingCategory;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found Exception");
		}
		
	}

}
