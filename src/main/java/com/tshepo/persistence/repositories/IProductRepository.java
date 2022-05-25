package com.tshepo.persistence.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tshepo.persistence.Product;

@Repository
@Transactional(readOnly = true)
public interface IProductRepository  extends CrudRepository<Product, Long>{
	
	Optional<Product> findByName(String characters);
	
	Optional<Product> findByProductId(String categoryId);

}