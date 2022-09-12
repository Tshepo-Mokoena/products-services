package com.tshepo.product.persistence;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Media {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false, unique = true)
	private Long id;
	
	@Column(name = "product_id", updatable = false, unique = true)
	private String mediaId;
	
	@Column(name = "media_url", nullable = true)
	private String mediaURL;
	
	@Column(name = "media_name", nullable = true)
	private String mediaName;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "product_id")
	private Product product;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	public static Media createdMedia(Product product, String mediaUrl, String mediaExtension) {
		Media media = new Media();
		media.setProduct(product);
		media.setMediaId(UUID.randomUUID().toString());
		media.setMediaURL(mediaUrl);
		media.setMediaName(media.getMediaId() + mediaExtension);
		media.setCreatedAt(LocalDateTime.now());
		return media;
	}
	
}
