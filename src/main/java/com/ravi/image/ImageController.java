package com.ravi.image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.activation.FileTypeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.image.helper.ImageHelper;
import com.ravi.image.valueobject.ImageResponse;

@RestController
public class ImageController {
	
	@Autowired
	ImageHelper imgHelp;

	@RequestMapping("/test/{imagename:.+}")
	public ResponseEntity<byte[]> getTestImage(@PathVariable("imagename") String imagename) throws IOException{
		//	System.out.println(imagename);
		File img = new File(imagename);
		//	    File img = new File("src/main/resources/static/test.jpg");

		return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
	}
	
	@RequestMapping(path="/refreshImages",produces = "application/json")
	public ResponseEntity refreshImage() throws IOException{
		
		ArrayList<ImageResponse> imgResp =  imgHelp.refreshImages();
				
		return ResponseEntity.ok().body(imgResp);
	}
	
	@RequestMapping(path="/retrieveAllImages",produces = "application/json")
	public ResponseEntity getAllImage() throws IOException{
		
		ArrayList<ImageResponse> imgResp =  imgHelp.refreshImages();
				
		return ResponseEntity.ok().body(imgResp);
	}
	
	@RequestMapping("/retrieveImage/{imageid}")
	public ResponseEntity<byte[]> getImage(@PathVariable("imageid") int imageid) throws IOException{
		
		File img = imgHelp.retrieveImage(imageid);
		
		return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
	}
	
	

}
