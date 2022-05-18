package com.example.springbootjpamanytomany.controller;

import com.example.springbootjpamanytomany.request.LikeRequest;
import com.example.springbootjpamanytomany.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController implements LikeControllerInterface{

    private final LikeService likeService;

    @Override
    public ResponseEntity<?> addLike(LikeRequest request) {
        return ResponseEntity.ok(likeService.addLike(request));

    }

    @Override
    public ResponseEntity<?> removeLike(LikeRequest request) {
        return ResponseEntity.ok("ok");
    }
}
