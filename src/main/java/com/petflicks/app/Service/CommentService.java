package com.petflicks.app.Service;

import com.petflicks.app.Models.Comment;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {



    Comment create(Comment newComment);
	Comment findById(int commentId);
    Comment update(Comment commentToEdit);
    void delete(Comment deleteComment);
}
