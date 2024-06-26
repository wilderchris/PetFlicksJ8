package com.petflicks.app.Service;

import com.petflicks.app.Models.Video;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface VideoService {

	public void uploadVideo(MultipartFile file);


    Video findById(int videoId);

    Video create(Video newVideo);

    Video update(Video videoToEdit);

    void delete(Video deleteVideo);
}
