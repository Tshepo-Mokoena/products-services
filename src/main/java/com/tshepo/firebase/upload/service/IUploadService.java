package com.tshepo.firebase.upload.service;

import org.springframework.web.multipart.MultipartFile;

import com.tshepo.firebase.upload.response.Upload;

public interface IUploadService {
		
	Upload uploadFile(MultipartFile file, String fileName);
	
	boolean deleteFile(String fileName);

}
