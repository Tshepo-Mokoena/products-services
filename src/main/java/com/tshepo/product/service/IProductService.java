package com.tshepo.product.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.tshepo.product.persistence.Product;

public interface IProductService {
	
	Product add(Product product);
	
	Product update(Product product);
		
	Optional<Product> findByProductId(String productId);
	
	Optional<Product> findByTitle(String title);
	
	Page<Product> findAll(String keyword, int evalPage, int evalPageSize);
	
	Page<Product> findActive(String keyword, int evalPage, int evalPageSize);
	
	Page<Product> findByActiveCategory(String keyword, int evalPage, int evalPageSize);
	
	Page<Product> findByCategory(String keyword, int evalPage, int evalPageSize);
	
	void updateStatus(String productId, Boolean active);
	
	void delete(Product product);

}
