package com.tshepo.product.service;

import java.util.List;
import java.util.Optional;

import com.tshepo.product.persistence.Category;

public interface ICategoryService {

	Category add(Category category);

	void update(Category category);
	
	List<Category> findAll();

	Optional<Category> findByCategoryId(String categoryId);

	Optional<Category> findByTitle(String title);

	void delete(Category category);

}
