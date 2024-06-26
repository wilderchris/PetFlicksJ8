package com.petflicks.app.Controller;

import com.petflicks.app.Models.Comment;
import com.petflicks.app.Models.Follower;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.petflicks.app.Service.FollowerService;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path="/follower/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class FollowerController {

    private FollowerService followerServ;

    public FollowerController() {
        super();
    }

    @PostMapping
    public ResponseEntity<Map<String,Integer>> create(@RequestBody Follower newFollower) {

        try
        {
            newFollower = followerServ.create(newFollower);
            Map<String, Integer> newIdMap = new HashMap<>();
            newIdMap.put("generatedId", newFollower.getFollowerId());
            return ResponseEntity.status(HttpStatus.CREATED).body(newIdMap);

        }catch (Exception e)	{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping(path="/v1/{commentId}")//
    public ResponseEntity<Follower> getCommentById(@PathVariable int followerId){
        System.out.println("get by comment id");
        Follower follower = followerServ.findById(followerId);
        if (follower != null) {
            return ResponseEntity.ok(follower);
        } else
            return ResponseEntity.notFound().build();
    }

    @PutMapping(path="/{followerId}")//
    public ResponseEntity<Follower> updateBoard(@PathVariable int followerId,
                                               @RequestBody Follower  followerToEdit) {

        if (followerToEdit != null && followerToEdit.getFollowerId() == followerId) {
            followerToEdit = followerServ.update(followerToEdit);

            if (followerToEdit != null)
                return ResponseEntity.ok(followerToEdit);
            else
                return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping(path="/{commentId}")//
    public ResponseEntity<Void> deleteComment(@RequestBody Follower deleteFollower) {
        if (deleteFollower != null) {
            followerServ.delete(deleteFollower);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
