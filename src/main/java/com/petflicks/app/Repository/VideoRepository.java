package com.petflicks.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petflicks.app.Models.Video;

public interface VideoRepository extends JpaRepository<Video, Integer>{

	

}