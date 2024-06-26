package com.petflicks.app.Models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Video {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int videoId;
	
	@Column(name="video_url")
	private String videoURL;
	@Column
	private int userId = 1;
	@Column
	private String description;
	
	
	
	
	public Video() {
		super();
	}


	public Video(int videoId, String videoURL, int userId, String description) {
		super();
		this.videoId = videoId;
		this.videoURL = videoURL;
		this.userId = userId;
		this.description = description;
	}
	
	
	public int getVideoId() {
		return videoId;
	}


	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}


	public String getVideoURL() {
		return videoURL;
	}


	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	

	@Override
	public int hashCode() {
		return Objects.hash(description, userId, videoId, videoURL);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Video other = (Video) obj;
		return Objects.equals(description, other.description) && userId == other.userId
				&& videoId == other.videoId && Objects.equals(videoURL, other.videoURL);
	}
	@Override
	public String toString() {
		return "Video [videoId=" + videoId + ", videoURL=" + videoURL + ", userId=" + userId + ", description="
				+ description + "]";
	}
	
	
	
}
