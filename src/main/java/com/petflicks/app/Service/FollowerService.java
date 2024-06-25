package com.petflicks.app.Service;

import com.petflicks.app.Models.Comment;
import com.petflicks.app.Models.Follower;
import org.springframework.stereotype.Service;

import com.petflicks.app.Exception.InvalidLoginException;
import com.petflicks.app.Exception.UserNotFoundException;
import com.petflicks.app.Exception.UsernameAlreadyExists;
import com.petflicks.app.Models.User;

@Service
public interface FollowerService {

    Follower create(Follower newFollower);

    Follower findById(int followerId);

    Follower update(Follower followerToEdit);

    void delete(Follower deleteFollower);

}
