package com.frederikzwartbol.springbootjpamanytomany.controller.tweet.operations;

import com.frederikzwartbol.springbootjpamanytomany.models.request.LikeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(TweetOperations.PREFIX )
public interface LikeOperations {
    String PREFIX = "/like";

    @PostMapping("/{tweetId}"+PREFIX)
    ResponseEntity<?> checkLike(@PathVariable("tweetId") int tweetId, @RequestBody LikeRequest request);

    @DeleteMapping("/{tweetId}"+PREFIX)
    ResponseEntity<?> deleteLike(@PathVariable("tweetId") @RequestBody LikeRequest request);

}
