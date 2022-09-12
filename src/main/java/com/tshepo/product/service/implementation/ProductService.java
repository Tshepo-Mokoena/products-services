package com.tshepo.product.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tshepo.product.persistence.Product;
import com.tshepo.product.persistence.repository.IProductRepository;
import com.tshepo.product.service.IMediaService;
import com.tshepo.product.service.IProductService;

@Service
public class ProductService implements IProductService{
	
	private IProductRepository productRepository;
	
	private IMediaService mediaService;
	
	@Autowired
	private void setProductService(IProductRepository productRepository, IMediaService mediaService) {
		this.productRepository = productRepository;
		this.mediaService = mediaService;
	}

	@Override
	public Product add(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product update(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Product> findByProductId(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Product> findByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> findAll(String keyword, int evalPage, int evalPageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> findActive(String keyword, int evalPage, int evalPageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> findByActiveCategory(String keyword, int evalPage, int evalPageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> findByCategory(String keyword, int evalPage, int evalPageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(String productId, Boolean active) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void delete(Product product) {
		productRepository.delete(product);
	}

}
