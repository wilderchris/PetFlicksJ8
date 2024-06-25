package com.petflicks.app.Controller;

import com.petflicks.app.Models.Video;
import com.petflicks.app.Service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path="/video/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class VideoController {

	private VideoService videoServ;

		public VideoController() {
		super();}

	@PostMapping
	public ResponseEntity<Map<String,Integer>> create(@RequestBody Video newVideo) {

		try
		{
			newVideo = videoServ.create(newVideo);
			Map<String, Integer> newIdMap = new HashMap<>();
			newIdMap.put("generatedId", newVideo.getVideoId());
			return ResponseEntity.status(HttpStatus.CREATED).body(newIdMap);

		}catch (Exception e)	{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@GetMapping(path="/v1/{videoId}")//
	public ResponseEntity<Video> getVideoById(@PathVariable int videoId){
		System.out.println("get by video id");
		Video video = videoServ.findById(videoId);
		if (video != null) {
			return ResponseEntity.ok(video);
		} else
			return ResponseEntity.notFound().build();
	}

	@PutMapping(path="/{videoId}")//
	public ResponseEntity<Video> updateVideo(@PathVariable int videoId,
												 @RequestBody Video videoToEdit) {

		if (videoToEdit != null && videoToEdit.getVideoId() == videoId) {
			videoToEdit = videoServ.update(videoToEdit);

			if (videoToEdit != null)
				return ResponseEntity.ok(videoToEdit);
			else
				return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@DeleteMapping(path="/{videoId}")//
	public ResponseEntity<Void> delete(@RequestBody Video deleteVideo) {
		if (deleteVideo != null) {
			videoServ.delete(deleteVideo);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

}
