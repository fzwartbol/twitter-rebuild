package com.frederikzwartbol.springboottwitterrebuild.features.tweet;

import com.frederikzwartbol.springboottwitterrebuild.features.tweet.models.dto.TweetDTO;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.models.dto.TweetMinimalDTO;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.models.dto.TweetRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(TweetOperations.PREFIX)
public interface TweetOperations {

    String PREFIX = "/tweets";

    @GetMapping
    ResponseEntity<List<TweetMinimalDTO>> findAllTweets();

    @PostMapping
    ResponseEntity<TweetDTO> saveTweet(@RequestBody TweetRequest request);

    @GetMapping("/{tweetId}")
    ResponseEntity<TweetDTO> findTweetById(@PathVariable("tweetId") Long tweetId);

    @PutMapping
    ResponseEntity<TweetDTO> updateTweet(@RequestBody TweetRequest request);

    @DeleteMapping("/{tweetId}")
    ResponseEntity<?> deleteTweetById(@PathVariable("tweetId") Long tweetId);

    @GetMapping("/paginated")
    ResponseEntity<Page<TweetMinimalDTO>> findAllTweetsPaginated(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy);

    @GetMapping("/user/{userId}/")
    ResponseEntity<Page<TweetMinimalDTO>> findTweetsUser(
            @PathVariable("userId") Long userId,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy);

    @GetMapping("/user/{userId}/with_replies")
    ResponseEntity<Page<TweetMinimalDTO>> findTweetsWithRepliesUser(
            @PathVariable("userId") Long userId,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy);

    @GetMapping("/user/{userId}/media")
    ResponseEntity<Page<TweetMinimalDTO>> findTweetsWithMediaUser(
            @PathVariable("userId") Long userId,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy);

    @GetMapping("liked/user/{userId}")
    ResponseEntity<Page<TweetMinimalDTO>> getLikedTweetsByUser(@PathVariable("userId") Long userId,
                                                               @RequestParam Optional<Integer> page,
                                                               @RequestParam Optional<String> sortBy);
}
