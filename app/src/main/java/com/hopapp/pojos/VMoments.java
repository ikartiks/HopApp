package com.hopapp.pojos;

import java.io.Serializable;
import java.util.Date;

public class VMoments extends VDataObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 437013550169954486L;
	
	protected Long userId;
	
	protected String imagePath;
	
	protected String videoPath;
	
	protected String description;
	
	protected Double latitude;
	
	protected Double longitude;
	
	protected Date createdAt;
	
	protected Date updatedAt;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Date getCreateTime() {
		return createdAt;
	}

	public void setCreateTime(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
}
