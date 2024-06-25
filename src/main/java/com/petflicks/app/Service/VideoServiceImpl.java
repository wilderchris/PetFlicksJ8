package com.petflicks.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.petflicks.app.Models.Video;
import com.petflicks.app.Repository.VideoRepository;

@Service
public class VideoServiceImpl implements VideoService {
	//Dotenv dotenv = Dotenv.configure().directory("./).filename(".env").load();// default values comment to have reminder

	
	private final S3ServiceImpl s3Service;
	private VideoRepository videoRepo;
	
	
	
	@Autowired
	public VideoServiceImpl(S3ServiceImpl s3Service, VideoRepository videoRepo) {
		super();
		this.s3Service = s3Service;
		this.videoRepo = videoRepo;
	}




	public void uploadVideo(MultipartFile file) {
		String videoURL = s3Service.uploadFile(file);
		Video video = new Video();
		video.setVideoURL(videoURL);
		
		videoRepo.save(video);
		// write to DB

	}

	@Override
	public Video findById(int videoId) {
		return null;
	}

	@Override
	public Video create(Video newVideo) {
		return null;
	}

	@Override
	public Video update(Video videoToEdit) {
		return null;
	}

	@Override
	public void delete(Video deleteVideo) {

	}

}
