package com.tshepo.product.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tshepo.product.persistence.Media;
import com.tshepo.product.persistence.Product;
import com.tshepo.product.persistence.repository.IMediaRepository;
import com.tshepo.product.service.IMediaService;

@Service
public class MediaService implements IMediaService{
	
	private IMediaRepository mediaRepository;
	
	@Autowired
	private void setMediaService(IMediaRepository mediaRepository) {
		this.mediaRepository = mediaRepository;
	}

	@Override
	public Media create(Media media) {
		return mediaRepository.save(media);
	}

	@Override
	public Media update(Media media) {
		return mediaRepository.save(media);
	}

	@Override
	public List<Media> findByProduct(Product product) {
		return mediaRepository.findByProduct(product);
	}

	@Override
	public Optional<Media> findByMediaId(String mediaId) {
		return mediaRepository.findByMediaId(mediaId);
	}

	@Override
	public void delete(Media media) {
		mediaRepository.delete(media);
	}

}
