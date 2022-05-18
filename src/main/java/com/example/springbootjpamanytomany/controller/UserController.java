package com.example.springbootjpamanytomany.controller;

import com.example.springbootjpamanytomany.enitity.User;
import com.example.springbootjpamanytomany.request.UserRequest;
import com.example.springbootjpamanytomany.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController implements UserControllerInterface {

    private final UserService userService;

    @Override
    public ResponseEntity<List<User>> findAllUsers() {
       return ResponseEntity.ok(userService.findAll());
    }

    @Override
    public ResponseEntity<User> saveUser(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.saveUser(request));
    }

    @Override
    public ResponseEntity<User> findUserById(Long userId) {
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @Override
    public ResponseEntity<User> updateUser(UserRequest request) {
        return ResponseEntity.ok(userService.updateUser(request));
    }

    @Override
    public ResponseEntity<?> deleteUserById(Long userId) {
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }


}
