package com.tshepo.product.service;

import java.util.List;
import java.util.Optional;

import com.tshepo.product.persistence.Media;
import com.tshepo.product.persistence.Product;

public interface IMediaService {
	
	Media create(Media media);
	
	Media update(Media media);
	
	List<Media> findByProduct(Product product);
	
	Optional<Media> findByMediaId(String mediaId);
	
	void delete(Media media);	

}
