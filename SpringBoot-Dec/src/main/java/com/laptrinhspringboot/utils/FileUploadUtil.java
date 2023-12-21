package com.laptrinhspringboot.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadUtil {
	
	
	
	
public File handleUploadFile( MultipartFile uploadFile) {
		
		String path = "C:\\Users\\meiou\\OneDrive\\デスクトップ\\JAVA-WEB-11-2023\\workspace2\\SpringBoot-Dec\\src\\main\\webapp\\storage";
		File myFile = new File(path);
		
		if(!myFile.exists()) {
			myFile.mkdirs();
		}
		
		File savedFile = null;

		try {
			
			String fileUploadName = uploadFile.getOriginalFilename();
			String fileExtension = fileUploadName.substring(fileUploadName.lastIndexOf('.') + 1);
			
			//substring(beginIndex, endIndex): Trả về một phần của chuỗi từ beginIndex đến endIndex - 1.
			
			/*
			 * for(int i = fileUploadName.length() - 1; i > 0; i--) {
			 * if(fileUploadName.charAt(i) == '.') { fileExtension +=
			 * fileUploadName.substring(i + 1); break; } }
			 */
			
			String randomStr = UUID.randomUUID().toString(); // sinh ra 1 chuỗi ngẫu nhiên
			
			String newFileName = randomStr + "." + fileExtension;
			
			savedFile = new File(myFile, newFileName);
			uploadFile.transferTo(savedFile);
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return savedFile;
	}
	
	
	// hard code path
	public File handleUploadFileV0( MultipartFile uploadFile) {
		
		String path = "C:\\Users\\meiou\\OneDrive\\デスクトップ\\JAVA-WEB-11-2023\\workspace2\\SpringBoot-Dec\\src\\main\\webapp\\storage";
		File myFile = new File(path);
		
		if(!myFile.exists()) {
			myFile.mkdirs();
		}
		
		File savedFile = new File(myFile, uploadFile.getOriginalFilename());
		/*
		 * For example, if myFile represents the directory "/path/to/uploads" and
		 * uploadFile.getOriginalFilename() returns "example.txt", then savedFile will
		 * represent the "path /path/to/uploads/example.txt"
		 */
		try {
			uploadFile.transferTo(savedFile);
			//transfers the content of the uploaded file (uploadFile) to the specified destination file (savedFile).
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return savedFile;
	}
	
}
