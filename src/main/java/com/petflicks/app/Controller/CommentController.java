package com.petflicks.app.Controller;


import com.petflicks.app.Models.Comment;
import com.petflicks.app.Service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path="/comment/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {
    private CommentService commentServ;

    public CommentController() {
        super();
    }

    @PostMapping
    public ResponseEntity<Map<String,Integer>> create(@RequestBody Comment newComment) {

        try
        {
            newComment = commentServ.create(newComment);
            Map<String, Integer> newIdMap = new HashMap<>();
            newIdMap.put("generatedId", newComment.getCommentId());
            return ResponseEntity.status(HttpStatus.CREATED).body(newIdMap);

        }catch (Exception e)	{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping(path="/v1/{commentId}")//
    public ResponseEntity<Comment> getCommentById(@PathVariable int commentId){
        System.out.println("get by comment id");
        Comment comment = commentServ.findById(commentId);
        if (comment != null) {
            return ResponseEntity.ok(comment);
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping(path="/{commentId}")//
    public ResponseEntity<Comment> updateComment(@PathVariable int commentId,
                                             @RequestBody Comment commentToEdit) {

        if (commentToEdit != null && commentToEdit.getCommentId() == commentId) {
            commentToEdit = commentServ.update(commentToEdit);

            if (commentToEdit != null)
                return ResponseEntity.ok(commentToEdit);
            else
                return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping(path="/{commentId}")//
    public ResponseEntity<Void> deleteComment(@RequestBody Comment deleteComment) {
        if (deleteComment != null) {
            commentServ.delete(deleteComment);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
