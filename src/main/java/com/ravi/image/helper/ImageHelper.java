package com.ravi.image.helper;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ravi.image.entity.Image;
import com.ravi.image.jpa.ImageRepoService;
import com.ravi.image.util.FileFilterUtil;
import com.ravi.image.valueobject.ImageResponse;

@Component
public class ImageHelper {

	@Autowired
	ImageRepoService imgRepSer;

	@Value("${imagepath}")
	String imageRootFolder;
	
	@Autowired
	Environment env;

	@Scheduled(fixedRate = 120000)
	public ArrayList<ImageResponse> refreshImages()
	{
		System.out.println("Refresh Image in progress.....");
		
		File file = new File(imageRootFolder);

		File[] filesListInDirectory = file.listFiles(new FileFilterUtil("jpg","jpeg","png"));

		ArrayList<Image> imgList = new ArrayList<Image>();

		ArrayList<ImageResponse> imgRespList = new ArrayList<ImageResponse>();

		for(File fileInfo:filesListInDirectory){

			Image img = new Image();
			img.setImageName(fileInfo.getName());
			img.setDescription(fileInfo.getName().substring(0,fileInfo.getName().indexOf(".")));
			img.setPath(fileInfo.getAbsolutePath());

			imgList.add(img);
		}

		imgRepSer.deleteAll();
		imgRepSer.insertAll(imgList);


		imgRepSer.getAllImages().forEach(img -> {
			//System.out.println(img.getId()+ " -- "+img.getImageName()+" -- "+img.getPath()+" -- "+img.getDescription());
			ImageResponse imgResp = new ImageResponse();

			imgResp.setImageDescription(img.getDescription());
			imgResp.setImageId(img.getId());
			imgResp.setImageName(img.getImageName());
			imgResp.setImagePath(img.getPath());

			imgRespList.add(imgResp);
		});
		
		/*
		 * imgRespList.forEach(img -> System.out.println(img.getImageId()+
		 * " -- "+img.getImageName()+" -- "+img.getImagePath()+" -- "+img.
		 * getImageDescription()));
		 */
		
		System.out.println("Refresh Image Completed.");
		
		return imgRespList;
	}
	
	public File retrieveImage(int imageid) {
		
		String imgPath = imgRepSer.getImageById(imageid).getPath();
		
		File img = new File(imgPath);
		
		return img;
	}
	
	public ArrayList<ImageResponse> retrieveAllImageInfo()
	{
		ArrayList<ImageResponse> imgRespList = new ArrayList<ImageResponse>();
		String accessUrl="http://";
		try {
			accessUrl+=InetAddress.getLocalHost().getHostName()+":"+env.getProperty("local.server.port")+"/retrieveImage/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final String extAccessUrl = accessUrl;
		
		imgRepSer.getAllImages().forEach(img -> {
			//System.out.println(img.getId()+ " -- "+img.getImageName()+" -- "+img.getPath()+" -- "+img.getDescription());
			
			ImageResponse imgResp = new ImageResponse();

			imgResp.setImageDescription(img.getDescription());
			imgResp.setImageId(img.getId());
			imgResp.setImageName(img.getImageName());
			imgResp.setImagePath(extAccessUrl+img.getId());

			imgRespList.add(imgResp);
		});
		
		return imgRespList;
	}
}
