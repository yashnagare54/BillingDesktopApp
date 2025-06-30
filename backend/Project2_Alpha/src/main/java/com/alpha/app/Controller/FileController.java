package com.alpha.app.Controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin(origins = "*")
public class FileController {

	// GET Image from image folder of all category, product
	private final Path imageFolder = Paths.get("images");

    @GetMapping("/api/images")
    public ResponseEntity<Resource> getImage(@RequestParam String imageName) {
        try {
            Path imagePath = imageFolder.resolve(imageName);
            File imageFile = imagePath.toFile();
            if (!imageFile.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            Resource resource = new org.springframework.core.io.FileSystemResource(imageFile);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/jpeg") // Adjust MIME type based on your image type
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // For GET Image of Bussiness Logo from business_logo folder
    private final Path businessLogoFolder = Paths.get("business_logo");
    
    @GetMapping("/api/business-logo")
    public ResponseEntity<Resource> getBusinessLogo(@RequestParam String businessLogo)
    {
    	try {
    		
    		Path logoPath = businessLogoFolder.resolve(businessLogo);
    		File logoFile = logoPath.toFile();
    		if(!logoFile.exists())
    		{
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    		}
    		Resource logoResource = new org.springframework.core.io.FileSystemResource(logoFile);
    		
    		return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/jpeg") // Adjust MIME type based on your image type
                    .body(logoResource);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
    }
  
}
