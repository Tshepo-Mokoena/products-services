package com.tshepo.product.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.tshepo.exception.NotFoundException;
import com.tshepo.product.persistence.Product;
import com.tshepo.product.service.IProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	private IProductService productService;
	
	@Autowired
	private void setProductController(IProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void addProduct(
			@RequestPart("product") Product product, 
			@RequestPart("files") Optional<List<MultipartFile>> files) {
		
		if (files.isPresent())
			productService.add(product, files.get());
		else
			productService.add(product);			
		
	}
	
	@PostMapping("/{productId}")
	@ResponseStatus(HttpStatus.OK)
	public void updateProduct(
			@PathVariable("productId") String productId,
			@RequestPart("product") Product product, 
			@RequestPart("files") Optional<List<MultipartFile>> files) {
		
		if (productService.findByProductId(productId).isEmpty())
			throw new NotFoundException(productId);
		
		if (productId.contains(product.getProductId())) {
			
			if (files.isPresent())
				productService.update(product, files.get());
			else
				productService.update(product);
			
		} else
			throw new NotFoundException(productId);					
		
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Page<Product> getProducts(
			@RequestParam Optional<String> keyword,
			@RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> pageSize){
		return productService.findAll(keyword.orElse(""), page.orElse(0), pageSize.orElse(10));
	}
	
	@GetMapping("/{productId}")
	@ResponseStatus(HttpStatus.OK)
	public Product getProduct(@PathVariable("productId") String productId){
		return productService.findByProductId(productId).orElseThrow(() -> new NotFoundException(productId));
	}
	
	@GetMapping("/active")
	@ResponseStatus(HttpStatus.OK)
	public Page<Product> getActive(
			@RequestParam Optional<String> keyword,
			@RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> pageSize){
		return productService.findActive(keyword.orElse(""), page.orElse(0), pageSize.orElse(10));
	}
	
	@GetMapping("/category")
	@ResponseStatus(HttpStatus.OK)
	public Page<Product> getCategory(
			@RequestParam Optional<String> keyword,
			@RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> pageSize){
		return productService.findByCategory(keyword.orElse(""), page.orElse(0), pageSize.orElse(10));
	}
	
	@GetMapping("/category/active")
	@ResponseStatus(HttpStatus.OK)
	public Page<Product> getActiveCategory(
			@RequestParam Optional<String> keyword,
			@RequestParam Optional<Integer> page,
			@RequestParam Optional<Integer> pageSize){
		return productService.findByActiveCategory(keyword.orElse(""), page.orElse(0), pageSize.orElse(10));
	}

}
