package com.tshepo.product.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tshepo.product.persistence.Media;
import com.tshepo.product.persistence.Product;

@Repository
@Transactional(readOnly = true)
public interface IMediaRepository extends CrudRepository<Media, Long>{
	
	Optional<Media> findByMediaId(String mediaId);
	
	List<Media> findByProduct(Product product);

}
