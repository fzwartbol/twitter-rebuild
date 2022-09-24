package com.frederikzwartbol.springbootjpamanytomany.controller.tweet.operations;

import com.frederikzwartbol.springbootjpamanytomany.models.DTO.TweetMinimalResponseDTO;
import com.frederikzwartbol.springbootjpamanytomany.models.DTO.TweetDetailResponseDTO;
import com.frederikzwartbol.springbootjpamanytomany.models.request.TweetRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(TweetOperations.PREFIX)
public interface TweetOperations {
    String PREFIX = "/tweet";

    @GetMapping
    ResponseEntity<List<TweetMinimalResponseDTO>> findAllTweets();

    @PostMapping
    ResponseEntity<TweetDetailResponseDTO> saveTweet(@RequestBody TweetRequest request);

    @GetMapping("/{tweetId}")
    ResponseEntity<TweetDetailResponseDTO> findTweetById(@PathVariable("tweetId") Long tweetId);

    @PutMapping
    ResponseEntity<TweetDetailResponseDTO> updateTweet(@RequestBody TweetRequest request);

    @DeleteMapping("/{tweetId}")
    ResponseEntity<?> deleteTweetById (@PathVariable("tweetId") Long tweetId);

    @GetMapping("/paginated")
    ResponseEntity<Page<TweetMinimalResponseDTO>> findAllTweetsPaginated(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy);

    @GetMapping("/user/{userId}/")
    ResponseEntity<Page<TweetMinimalResponseDTO>> findTweetsUser(
            @PathVariable("userId") Long userId,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy);

    @GetMapping("/user/{userId}/with_replies")
    ResponseEntity<Page<TweetMinimalResponseDTO>> findTweetsWithRepliesUser(
            @PathVariable("userId") Long userId,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy);

    @GetMapping("/user/{userId}/media")
    ResponseEntity<Page<TweetMinimalResponseDTO>> findTweetsWithMediaUser(
            @PathVariable("userId") Long userId,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy);

    @GetMapping("liked/user/{userId}")
    ResponseEntity<Page<TweetMinimalResponseDTO>> getLikedTweetsByUser(@PathVariable("userId") Long userId,
                                                                       @RequestParam Optional<Integer> page,
                                                                       @RequestParam Optional<String> sortBy);
}
