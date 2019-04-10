package com.ravi.image.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ravi.image.entity.Image;

@Component
public class ImageRepoService {

	@Autowired
	ImageRepository imgRepo;
	
	public List<Image> getAllImages() {
		List<Image> img = new ArrayList<Image>();
		imgRepo.findAll().forEach(imge -> img.add(imge));
		return img;
	}

	public Image getImageById(int id) {
		return imgRepo.findOne(id);
	}

	public void insertAll(ArrayList<Image> imgList)
	{
		imgRepo.save(imgList);
	}
	
	public void deleteAll()
	{
		imgRepo.deleteAll();
	}

}
