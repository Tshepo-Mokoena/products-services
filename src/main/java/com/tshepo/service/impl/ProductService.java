package com.tshepo.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tshepo.persistence.Product;
import com.tshepo.persistence.repositories.IProductRepository;
import com.tshepo.service.IProductService;

@Service
public class ProductService implements IProductService{
	
	private IProductRepository productRepository;
	
	@Autowired
	private ProductService(IProductRepository productRepository)	
	{
		this.productRepository = productRepository;
	}
	
	@Override
	public Product saveProduct(Product product) 
	{		
		product.setActive(false);
		product.setCreatedAt(LocalDateTime.now());
		return productRepository.save(product);
	}
	
	@Override
	public List<Product> findAll() {
		
		return (List<Product>)productRepository.findAll();
	}
	
	@Override
	public Optional<Product> findByProductId(String productId) 
	{
		return productRepository.findByProductId(productId);
	}
	
	@Override
	public Optional<Product> findByProductName(String name) 
	{
		return productRepository.findByProductName(name);
	}
	
	
	@Override
	public List<Product> productSearch(String name) 
	{
		return productRepository.findByNameContaining(name);
	}
	
	@Override
	public List<Product> activeProductList() 
	{
		List<Product> categories = new ArrayList<>();
		List<Product> findCategories = (List<Product>)findAll();
		for(Product product: findCategories)
		{
			if (product.isActive())
				categories.add(product);
		}
		return categories;
	}

}
