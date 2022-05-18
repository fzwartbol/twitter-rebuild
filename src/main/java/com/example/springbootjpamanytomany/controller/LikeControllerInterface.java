package com.example.springbootjpamanytomany.controller;

import com.example.springbootjpamanytomany.request.LikeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface LikeControllerInterface {

    @PostMapping("/like")
    ResponseEntity<?> addLike(@RequestBody LikeRequest request);

    @DeleteMapping("/like")
    ResponseEntity<?> removeLike(@RequestBody LikeRequest request);
}
