package com.example.springbootjpamanytomany.controller;

import com.example.springbootjpamanytomany.entity.User;
import com.example.springbootjpamanytomany.request.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserControllerInterface {

    @GetMapping("/user")
    ResponseEntity<List<User>> findAllUsers();

    @PostMapping("/user")
    ResponseEntity<User> saveUser(@RequestBody UserRequest request);

    @GetMapping("/user/{userId}")
    ResponseEntity<User> findUserById (@PathVariable("userId") Long userId);

    @PutMapping("/user/{userId}")
    ResponseEntity<User> updateUser(@RequestBody UserRequest request);

    @DeleteMapping("/user/{userId}")
    ResponseEntity<?> deleteUserById (@PathVariable("userId") Long userId);
}
