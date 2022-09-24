package com.frederikzwartbol.springbootjpamanytomany.controller.tweet.operations;

import com.frederikzwartbol.springbootjpamanytomany.models.request.ReplyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping( TweetOperations.PREFIX)
public interface ReplyOperations {
    String PREFIX = "/reply";

    @PostMapping("/{tweetId}"+PREFIX)
    ResponseEntity<?> addReply(@PathVariable("tweetId") Long id,@RequestBody ReplyRequest request);

    @DeleteMapping("/{tweetId}"+PREFIX)
    ResponseEntity<?> deleteReply(@PathVariable("tweetId") Long id, @RequestBody ReplyRequest request);
}
