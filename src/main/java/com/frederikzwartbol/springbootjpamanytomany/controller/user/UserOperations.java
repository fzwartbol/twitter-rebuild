package com.frederikzwartbol.springbootjpamanytomany.controller.user;

import com.frederikzwartbol.springbootjpamanytomany.models.entity.user.User;
import com.frederikzwartbol.springbootjpamanytomany.models.request.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping(UserOperations.PREFIX)
public interface UserOperations {
    String PREFIX = "/user";

    @GetMapping
    ResponseEntity<List<User>> findAllUsers();

    @PostMapping
    ResponseEntity<User> saveUser(@RequestBody UserRequest request);

    @GetMapping("/{userId}")
    ResponseEntity<User> findUserById (@PathVariable("userId") Long userId);

    @GetMapping("/handle/{twitterHandle}")
    ResponseEntity<User> findUserByHandle (@PathVariable("twitterHandle") String twitterHandle);

    @PutMapping("/{userId}")
    ResponseEntity<User> updateUser(@RequestBody UserRequest request);

    @DeleteMapping("/{userId}")
    ResponseEntity<?> deleteUserById (@PathVariable("userId") Long userId);

    @PostMapping("/{followUserId}/follow/{userId}")
    ResponseEntity<?> followUser (@PathVariable("userId") Long userId, @PathVariable("followUserId") Long followUserId);

    @PostMapping("/{followUserId}/unfollow/{userId}")
    ResponseEntity<?> unfollowUser (@PathVariable("userId") Long userId, @PathVariable("followUserId") Long unfollowUserId);

}
