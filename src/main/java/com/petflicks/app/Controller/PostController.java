package com.petflicks.app.Controller;

import com.petflicks.app.Models.Comment;
import com.petflicks.app.Models.Post;
import com.petflicks.app.Service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    private PostService postServ;

    public PostController() {
        super();
    }

    @PostMapping
    public ResponseEntity<Map<String,Integer>> create(@RequestBody Post newPost) {

        try
        {
            newPost = postServ.create(newPost);
            Map<String, Integer> newIdMap = new HashMap<>();
            newIdMap.put("generatedId", newPost.getPostId());
            return ResponseEntity.status(HttpStatus.CREATED).body(newIdMap);

        }catch (Exception e)	{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping(path="/v1/{postId}")//
    public ResponseEntity<Post> getPostById(@PathVariable int postId){
        System.out.println("get by post id");
        Post post = postServ.findById(postId);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping(path="/{postId}")//
    public ResponseEntity<Post> updatePost(@PathVariable int postId,
                                            @RequestBody Post postToEdit) {

        if (postToEdit != null && postToEdit.getPostId() == postId) {
            postToEdit = postServ.update(postToEdit);

            if (postToEdit != null)
                return ResponseEntity.ok(postToEdit);
            else
                return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping(path="/{postId}")//
    public ResponseEntity<Void> deletePost(@RequestBody Post deletePost) {
        if (deletePost != null) {
            postServ.delete(deletePost);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
