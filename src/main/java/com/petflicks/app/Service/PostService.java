package com.petflicks.app.Service;

import com.petflicks.app.Models.Comment;
import com.petflicks.app.Models.Post;
import org.springframework.stereotype.Service;

import com.petflicks.app.Exception.InvalidLoginException;
import com.petflicks.app.Exception.UserNotFoundException;
import com.petflicks.app.Exception.UsernameAlreadyExists;
import com.petflicks.app.Models.User;

@Service
public interface PostService {

    Post create(Post newPost);
	Post findById(int postId);
	Post update(Post postToEdit);
	void delete(Post deletePost);
}
