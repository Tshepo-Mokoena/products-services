package com.tshepo.product.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tshepo.exception.NotFoundException;
import com.tshepo.firebase.upload.response.Upload;
import com.tshepo.firebase.upload.service.IUploadService;
import com.tshepo.product.persistence.Category;
import com.tshepo.product.persistence.Media;
import com.tshepo.product.persistence.Product;
import com.tshepo.product.persistence.repository.IProductRepository;
import com.tshepo.product.service.ICategoryService;
import com.tshepo.product.service.IMediaService;
import com.tshepo.product.service.IProductService;

@Service
public class ProductService implements IProductService {

	private IProductRepository productRepository;

	private IMediaService mediaService;

	private ICategoryService categoryService;

	@Autowired
	private void setProductService(IProductRepository productRepository, IMediaService mediaService,
			ICategoryService categoryService) {
		this.productRepository = productRepository;
		this.mediaService = mediaService;
		this.categoryService = categoryService;
	}

	@Override
	public Product add(Product product) {
		return productRepository.save(Product.setProduct(product));
	}

	@Transactional
	@Override
	public Product add(Product product, List<MultipartFile> files) {

		Product newProduct = Product.setProduct(product);

		for (MultipartFile file : files) {

			mediaService.create(newProduct, file);

		}

		return productRepository.save(newProduct);
	}

	@Override
	public Product update(Product product) {
		return productRepository.save(product);
	}

	@Async
	@Transactional
	@Override
	public Product update(Product product, List<MultipartFile> files) {

		for (MultipartFile file : files) {

			mediaService.create(product, file);

		}

		return productRepository.save(product);
	}

	@Override
	public Optional<Product> findByProductId(String productId) {
		return productRepository.findByProductId(productId);
	}

	@Override
	public Optional<Product> findByTitle(String title) {
		return productRepository.findByTitle(title);
	}

	@Override
	public Page<Product> findAll(String keyword, int evalPage, int evalPageSize) {
		return productRepository.findByTitleContaining(keyword, PageRequest.of(evalPage, evalPageSize));
	}

	@Override
	public Page<Product> findActive(String keyword, int evalPage, int evalPageSize) {
		return productRepository.findByTitleContainingAndActive(keyword, true, PageRequest.of(evalPage, evalPageSize));
	}

	@Override
	public Page<Product> findByActiveCategory(String keyword, int evalPage, int evalPageSize) {
		return productRepository.findByCategoriesAndActive(findCategory(keyword), true,
				PageRequest.of(evalPage, evalPageSize));
	}

	@Override
	public Page<Product> findByCategory(String keyword, int evalPage, int evalPageSize) {
		return productRepository.findByCategories(findCategory(keyword), PageRequest.of(evalPage, evalPageSize));
	}

	@Override
	public void updateStatus(String productId, Boolean active) {
		productRepository.activateProduct(productId, active);
	}

	/**
	 * Deletes Product and Cloud Files if Present
	 */
	@Async
	@Transactional
	@Override
	public void delete(Product product) {

		List<Media> mediaList = mediaService.findByProduct(product);

		if (!mediaList.isEmpty()) {

			for (Media media : mediaList) {

				mediaService.delete(media);

			}

			productRepository.delete(product);

		} else {

			productRepository.delete(product);

		}

	}

	private Category findCategory(String title) {
		return categoryService.findByTitle(title).orElseThrow(() -> new NotFoundException(title));
	}

}
