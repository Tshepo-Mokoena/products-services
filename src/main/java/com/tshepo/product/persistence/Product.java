package com.tshepo.product.persistence;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false, unique = true)
	private Long id;
	
	@Column(name = "product_id", updatable = false, unique = true)
	private String productId;
	
	@NotBlank(message = "title should atleast contain 2 to 100 characters")
	@Size(min = 2, max = 100, message = "title should atleast contain 2 to 100 characters")
	@Column(name = "title", updatable = true, nullable = false)
	private String title;
	
	@NotBlank(message = "title should atleast contain 2 to 100 characters")
	@Size(min = 2, max = 100, message = "title should atleast contain 2 to 100 characters")
	@Column(name = "sub_title", updatable = true, nullable = false)
	private String subTitle;
	
	@NotBlank(message = "description should atleast contain 2 or more characters")
	@Size(min = 2, message = "description should atleast contain 2 or more characters")
	@Column(name = "description", updatable = true, nullable = false)
	private String desc;
	
	@ManyToMany
    @JoinTable(
    		name = "product_categories", 
    		joinColumns = @JoinColumn(name = "product_id"), 
    		inverseJoinColumns = @JoinColumn(name = "category_id")
    		)
    private List<Category> categories = new ArrayList<>();
		
	@Column(name = "price", nullable = false)
	private BigDecimal price;
	
	@Column(name = "quantity", nullable = false)
	private Integer qty;
		
	@Column(name = "discount", nullable = false)
	private BigDecimal discount;	

	@Column(name = "active", nullable = false)
	private Boolean active = false;
	
	@Column(name = "published_at", nullable = false)
	private LocalDateTime publishedAt;
	
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	public static Product setProduct(Product product) {
		Product setProduct = new Product();
		setProduct.setActive(product.getActive());
		setProduct.setCategories(product.getCategories());
		setProduct.setCreatedAt(LocalDateTime.now());
		setProduct.setDesc(product.getDesc());
		setProduct.setDiscount(product.getDiscount());
		setProduct.setPrice(product.getPrice());
		setProduct.setProductId(UUID.randomUUID().toString());
		setProduct.setPublishedAt(LocalDateTime.now());
		setProduct.setQty(product.getQty());
		setProduct.setSubTitle(product.getSubTitle());
		setProduct.setTitle(product.getTitle());
		setProduct.setUpdatedAt(LocalDateTime.now());
		return setProduct;
	}

}
