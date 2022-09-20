package com.tshepo.product.persistence;

import java.time.LocalDateTime;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
@Table(name = "category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false, unique = true)
	private Long id;
	
	@Column(name = "product_id", updatable = false, unique = true)
	private String categoryId;
	
	@NotBlank(message = "title should atleast contain 2 to 100 characters")
	@Size(min = 2, max = 100, message = "title should atleast contain 2 to 100 characters")
	@Column(name = "title", updatable = true, nullable = false)
	private String title;
	
	@ManyToMany
    @JoinTable(
    		name = "product_categories", 
    		joinColumns = @JoinColumn(name = "category_id"), 
    		inverseJoinColumns = @JoinColumn(name = "product_id")
    		)
	@JsonIgnore
    private List<Product> products;
	

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

}
