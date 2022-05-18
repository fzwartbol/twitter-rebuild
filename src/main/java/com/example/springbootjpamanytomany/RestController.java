package com.example.springbootjpamanytomany;


import com.example.springbootjpamanytomany.entity.Tweet;
import com.example.springbootjpamanytomany.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
        @Autowired
        private TweetRepository tweetRepository;

        @GetMapping("/posts")
        ResponseEntity<List<Tweet>> getAll () {
            return ResponseEntity.ok().body(tweetRepository.findAll());
        }
}
