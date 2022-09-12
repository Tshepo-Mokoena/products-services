package com.tshepo.product.persistence.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tshepo.product.persistence.Category;

@Repository
@Transactional(readOnly = true)
public interface ICategoryRepository extends CrudRepository<Category, Long>{
	
	Optional<Category> findByTitle(String title);
	
	Optional<Category> findByCategoryId(String categoryId);

	Page<Category> findByTitleContaining(String keyword, Pageable pageandSize);

}
