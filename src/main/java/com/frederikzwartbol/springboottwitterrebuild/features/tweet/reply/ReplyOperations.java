package com.frederikzwartbol.springboottwitterrebuild.features.tweet.reply;

import com.frederikzwartbol.springboottwitterrebuild.features.tweet.TweetOperations;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.reply.models.ReplyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(TweetOperations.PREFIX)
public interface ReplyOperations {
    String POSTFIX = "/reply";

    @PostMapping("/{tweetId}" + POSTFIX)
    ResponseEntity<?> addReply(@PathVariable("tweetId") Long id, @RequestBody ReplyRequest request);

    @DeleteMapping("/{tweetId}" + POSTFIX)
    ResponseEntity<?> deleteReply(@PathVariable("tweetId") Long id, @RequestBody ReplyRequest request);
}
