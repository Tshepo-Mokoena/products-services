package com.tshepo.firebase.upload.service.implementation;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.tshepo.firebase.upload.response.Upload;
import com.tshepo.firebase.upload.service.IUploadService;

@Service
public class UploadService implements IUploadService{
	
	private Storage storage;
		
	@EventListener
    public void initialize(ApplicationReadyEvent event)
    {
    	try {
    		FileInputStream serviceAccount =
    				  new FileInputStream("./tshepo-9129c-firebase-adminsdk-su9wh-c8876f7600.json");    		
    		storage = StorageOptions.newBuilder()
    				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
    				.setProjectId("tshepo-9129c")
    				.build().getService();			
		} catch (Exception e) {e.printStackTrace();}
    }

	@Async
	@Override
	public Upload uploadFile(MultipartFile file, String fileName) {
		
		String generatedFileName = generateFileName(file.getOriginalFilename(), fileName);
		
        BlobId blobId = BlobId.of(getBucketName(), generatedFileName);
        
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType())
                .build();
        try {
        	
			storage.create(blobInfo, file.getInputStream());
			
		} catch (IOException e) {e.printStackTrace();}
        
        String fileUrl = generateFileUrl(getBucketName(), generatedFileName);
        
        Upload upload = new Upload();
        upload.setFileName(generatedFileName);
        upload.setUrl(fileUrl);
        
        return upload;
	}

	@Async
	@Override
	public boolean deleteFile(String fileName) {
		
		BlobId blobId = BlobId.of(getBucketName(), fileName);
		
		boolean result =  storage.delete(blobId);
		
		if (result == true) return true;
		
		return false;
	}
	
	private String generateFileName(String originalFileName, String fileName) {
        return fileName + "." + getExtension(originalFileName);
    }
	
	private String generateFileUrl(String bucketName, String fileName) {
		return "https://firebasestorage.googleapis.com/v0/b/" + bucketName + "/o/" + fileName + "?alt=media";
	}
	
	private String getBucketName() { return "tshepo-9129c.appspot.com"; }
	
	public String getExtension(String originalFileName) {
        return StringUtils.getFilenameExtension(originalFileName);
    }

}
