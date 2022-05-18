package com.example.springbootjpamanytomany.controller;

import com.example.springbootjpamanytomany.entity.Tweet;
import com.example.springbootjpamanytomany.request.TweetRequest;
import com.example.springbootjpamanytomany.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
public class TweetController implements TweetControllerInterface{

    private final TweetService tweetService;

    @Override
    public ResponseEntity<List<Tweet>> findAllTweets() {
        return ResponseEntity.ok(tweetService.findAll());
    }

    @Override
    public ResponseEntity<Tweet> saveTweet(TweetRequest request) {
        return ResponseEntity.ok(tweetService.saveTweet(request));
    }

    @Override
    public ResponseEntity<Tweet> findTweetById(Long tweetId) {
        return ResponseEntity.ok(tweetService.findTweetById(tweetId));
    }

    @Override
    public ResponseEntity<Tweet> updateTweet(TweetRequest request) {
        return ResponseEntity.ok(tweetService.updateTweet(request));
    }

    @Override
    public ResponseEntity<?> deleteTweetById(Long tweetId) {
        tweetService.deleteTweetById(tweetId);
        return ResponseEntity.ok("Tweet deleted");
    }

    @Override
    public ResponseEntity<?> findTweetsPaginated(Optional<Integer> page, Optional<String> sortBy, List<String> hashtag) {
        return ResponseEntity.ok(tweetService.findTweetsByHashtag(hashtag));
    }

    @Override
    public ResponseEntity<Page<Tweet>> findAllTweetsPaginated(Optional<Integer> page, Optional<String> sortBy) {
        return ResponseEntity.ok(tweetService.findAllPaginated(page,sortBy));
    }


}
