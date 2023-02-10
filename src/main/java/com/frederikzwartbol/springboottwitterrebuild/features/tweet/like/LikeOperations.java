package com.frederikzwartbol.springboottwitterrebuild.features.tweet.like;

import com.frederikzwartbol.springboottwitterrebuild.features.tweet.TweetOperations;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.like.model.LikeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(TweetOperations.PREFIX)
public interface LikeOperations {
    String POSTFIX = "/like";

    @PostMapping("/{tweetId}" + POSTFIX)
    ResponseEntity<?> checkLike(@PathVariable("tweetId") int tweetId, @RequestBody LikeRequest request);

    @DeleteMapping("/{tweetId}" + POSTFIX)
    ResponseEntity<?> deleteLike(@PathVariable("tweetId") @RequestBody LikeRequest request);

}
