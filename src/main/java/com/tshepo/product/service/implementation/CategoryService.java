package com.tshepo.product.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tshepo.product.persistence.Category;
import com.tshepo.product.persistence.repository.ICategoryRepository;
import com.tshepo.product.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService{
	
	private ICategoryRepository categoryRepository;
	
	@Autowired
	private void setCategoryService(ICategoryRepository categoryRepository)	{this.categoryRepository = categoryRepository;}

	@Override
	public Category add(Category category) {
		category.setCategoryId(UUID.randomUUID().toString());
		category.setUpdatedAt(LocalDateTime.now());
		category.setCreatedAt(LocalDateTime.now());
		return categoryRepository.save(category);
	}

	@Override
	public void update(Category category) {
		category.setUpdatedAt(LocalDateTime.now());
		categoryRepository.save(category);
	}

	@Override
	public List<Category> findAll() {
		return (List<Category>)categoryRepository.findAll();
	}

	@Override
	public Optional<Category> findByCategoryId(String categoryId) {
		return categoryRepository.findByCategoryId(categoryId);
	}

	@Override
	public Optional<Category> findByTitle(String title) {
		return categoryRepository.findByTitle(title);
	}

	@Override
	public void delete(Category category) {
		categoryRepository.delete(category);
	}	

}
