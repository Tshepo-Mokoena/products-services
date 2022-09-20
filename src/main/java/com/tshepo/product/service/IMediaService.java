package com.tshepo.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.tshepo.product.persistence.Media;
import com.tshepo.product.persistence.Product;

public interface IMediaService {
	
	Media create(Product product, MultipartFile file);
	
	Media update(Media media, MultipartFile file);
	
	List<Media> findByProduct(Product product);
	
	Optional<Media> findByMediaId(String mediaId);
	
	void delete(Media media);	

}
