package com.tshepo.product.persistence.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tshepo.product.persistence.Category;
import com.tshepo.product.persistence.Product;

@Repository
@Transactional(readOnly = true)
public interface IProductRepository extends PagingAndSortingRepository<Product, Long>{
	
	Page<Product> findByTitleContaining(String keyword, Pageable pageable);
	
	Page<Product> findByTitleContainingAndActive(String title, Boolean active, Pageable pageable);
	
	Page<Product> findByCategoriesAndActive(Category category, Boolean active, Pageable pageable);
	
	Page<Product> findByCategories(Category category, Pageable pageable);
	
	@Modifying
	@Query("update Product set active = :active where productId = :productId")
	void activateProduct(@Param("productId") String productId, @Param("active") Boolean active);
	
	Optional<Product> findByProductId(String productId);

	Optional<Product> findByTitle(String title);	

}
