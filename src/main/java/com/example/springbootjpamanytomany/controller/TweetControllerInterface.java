package com.example.springbootjpamanytomany.controller;

import com.example.springbootjpamanytomany.enitity.Tweet;
import com.example.springbootjpamanytomany.request.TweetRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


public interface TweetControllerInterface {

    @GetMapping("/tweet")
    ResponseEntity<List<Tweet>> findAllTweets();

    @PostMapping("/tweet")
    ResponseEntity<Tweet> saveTweet(@RequestBody TweetRequest request);

    @GetMapping("/tweet/{tweetId}")
    ResponseEntity<Tweet> findTweetById(@PathVariable("tweetId") Long tweetId);

    @PutMapping("/tweet/")
    ResponseEntity<Tweet> updateTweet(@RequestBody TweetRequest request);

    @DeleteMapping("/tweet/{tweetId}")
    ResponseEntity<?> deleteTweetById (@PathVariable("tweetId") Long tweetId);

    @GetMapping("/search")
    ResponseEntity<?> findTweetsPaginated(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy,
            @RequestParam(defaultValue = "News") List<String> hashtag);

    @GetMapping("/tweet/paginated/")
    ResponseEntity<Page<Tweet>> findAllTweetsPaginated(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy);
}
