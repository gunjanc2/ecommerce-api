package com.gc2project.ecommerce.service;

import java.util.List;

import com.gc2project.ecommerce.model.Category;

public interface CategoryService {

	List<Category> getAllCategories();
	void createCategory(Category category);
	String deleteCategory(Long categoryId);
}
