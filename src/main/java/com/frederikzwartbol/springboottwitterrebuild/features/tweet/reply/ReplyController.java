package com.frederikzwartbol.springboottwitterrebuild.features.tweet.reply;

import com.frederikzwartbol.springboottwitterrebuild.features.tweet.TweetService;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.reply.models.ReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReplyController implements ReplyOperations {
    private final TweetService tweetService;

    @Override
    public ResponseEntity<?> addReply(@PathVariable("tweetId") Long id, ReplyRequest request) {
        return ResponseEntity.ok(
                tweetService.saveReplyToTweet(id, request));
    }
    @Override
    public ResponseEntity<?> deleteReply(Long id, ReplyRequest request) {
        return null;
    }

}
