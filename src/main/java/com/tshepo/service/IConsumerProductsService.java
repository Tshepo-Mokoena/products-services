package com.tshepo.service;

import java.net.URI;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tshepo.persistence.Product;

public interface IConsumerProductsService {

	URI createNewProduct(Product product, MultipartFile image);

	Product getByProductId(String productId);

	List<Product> getProducts();

}
