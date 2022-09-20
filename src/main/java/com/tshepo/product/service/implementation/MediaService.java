package com.tshepo.product.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tshepo.firebase.upload.response.Upload;
import com.tshepo.firebase.upload.service.IUploadService;
import com.tshepo.product.persistence.Media;
import com.tshepo.product.persistence.Product;
import com.tshepo.product.persistence.repository.IMediaRepository;
import com.tshepo.product.service.IMediaService;

@Service
public class MediaService implements IMediaService{
	
	private IMediaRepository mediaRepository;
	
	private IUploadService uploadService;
	
	@Autowired
	private void setMediaService(IMediaRepository mediaRepository, IUploadService uploadService) {
		this.mediaRepository = mediaRepository;
		this.uploadService = uploadService;
	}

	@Async
	@Transactional
	@Override
	public Media create(Product product, MultipartFile file) {
		
		String mediaId = UUID.randomUUID().toString();
		
		Upload upload = uploadService.uploadFile(file, mediaId);
		
		Media media = Media.createdMedia(product, mediaId ,  upload.getUrl(), upload.getFileName());
		
		return mediaRepository.save(media);
	}

	@Async
	@Transactional
	@Override
	public Media update(Media media, MultipartFile file) {
		uploadService.deleteFile(media.getMediaName());
		uploadService.uploadFile(file, media.getMediaName());
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

	@Async
	@Transactional
	@Override
	public void delete(Media media) {
		uploadService.deleteFile(media.getMediaName());
		mediaRepository.delete(media);
	}

}
