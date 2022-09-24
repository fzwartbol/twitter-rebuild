package com.frederikzwartbol.springbootjpamanytomany.controller.user;

import com.frederikzwartbol.springbootjpamanytomany.models.entity.user.User;
import com.frederikzwartbol.springbootjpamanytomany.models.request.UserRequest;
import com.frederikzwartbol.springbootjpamanytomany.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController implements UserOperations{

    private final UserService userService;

    @Override
    public ResponseEntity<List<User>> findAllUsers() {
       return ResponseEntity.ok(userService.findAll());
    }

    @Override
    public ResponseEntity<User> saveUser(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.saveUserFromRequest(request));
    }

    @Override
    public ResponseEntity<User> findUserById(Long userId) {
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @Override
    public ResponseEntity<User> findUserByHandle(String twitterHandle) {
        return ResponseEntity.ok(userService.findUserByHandle(twitterHandle));
    }

    @Override
    public ResponseEntity<User> updateUser(UserRequest request) {
        return ResponseEntity.ok(userService.updateUser(request));
    }

    @Override
    public ResponseEntity<?> deleteUserById(Long userId) {
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }

    @Override
    public ResponseEntity<?> followUser(Long userId, Long followUserId) {
        return ResponseEntity.ok(userService.followUser(userId,followUserId));
    }

    @Override
    public ResponseEntity<?> unfollowUser(Long userId, Long unfollowUserId) {
        return ResponseEntity.ok(userService.unfollowUser(userId,unfollowUserId));
    }

}
