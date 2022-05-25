package com.tshepo.service.impl;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tshepo.exception.NoProductsFoundException;
import com.tshepo.exception.ProductNameExistException;
import com.tshepo.exception.ProductNotFoundException;
import com.tshepo.persistence.Product;
import com.tshepo.service.IConsumerProductsService;
import com.tshepo.service.IProductService;
import com.tshepo.service.IUploadDowloadService;
import com.tshepo.util.Utilities;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ConsumerProductsService implements IConsumerProductsService{
	
	private IProductService productService;
	
	private IUploadDowloadService uploadDowloadService;
	
	@Autowired
	private ConsumerProductsService(IProductService productService,IUploadDowloadService uploadDowloadService) 
	{
		this.productService = productService;
		this.uploadDowloadService = uploadDowloadService;
	}

	@Override
	public URI createNewProduct(Product product, MultipartFile image) 
	{
		if(!productService.findByProductName(product.getName()).isEmpty())
			throw new ProductNameExistException(product.getName());
		
		String productId = Utilities.generateUniqueNumericUUId();
		
		String getImageUrl = uploadDowloadService.uploadFile(image, productId);
		
		if(getImageUrl != null)
			product.setProductImageURL(getImageUrl);
		
		product.setProductId(productId);
		
		Product savedProduct = productService.newProduct(product);
		
		URI locationUri = 
				ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{productId}")
				.buildAndExpand(savedProduct.getProductId()).toUri();
		return locationUri;
	}

	@Override
	public Product getByProductId(String productId) 
	{
		if(productId.isBlank())
			throw new IllegalArgumentException("product should not be blank");
		
		Product getProduct = productService.findByProductId(productId)
				.orElseThrow(() -> new ProductNotFoundException(productId));
				
		return getProduct;
	}

	@Override
	public List<Product> getProducts() 
	{		
		List<Product> getProductList = productService.findAll();
		if(!getProductList.isEmpty())
			throw new NoProductsFoundException();
		return getProductList;
	}

}
