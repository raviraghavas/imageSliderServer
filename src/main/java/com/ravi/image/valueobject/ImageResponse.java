package com.ravi.image.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageResponse {

	@JsonProperty("title")
	String imageName;

	@JsonProperty("id")
	int imageId;
	
	@JsonProperty("description")
	String imageDescription;
	
	@JsonProperty("image")
	String imagePath;

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the imageId
	 */
	public int getImageId() {
		return imageId;
	}

	/**
	 * @param imageId the imageId to set
	 */
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	/**
	 * @return the imageDescription
	 */
	public String getImageDescription() {
		return imageDescription;
	}

	/**
	 * @param imageDescription the imageDescription to set
	 */
	public void setImageDescription(String imageDescription) {
		this.imageDescription = imageDescription;
	}

	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	
}

