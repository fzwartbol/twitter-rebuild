package com.frederikzwartbol.springbootjpamanytomany.controller.user.feed;

import com.frederikzwartbol.springbootjpamanytomany.service.FeedService;
import com.frederikzwartbol.springbootjpamanytomany.util.mapper.TweetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class FeedController implements FeedOperations{

    private final FeedService feedService;

    @Override
    public ResponseEntity<?> findFeedOfUser(Long userId, Optional<Integer> page, Optional<String> sortBy) {
        return ResponseEntity.ok(feedService.getFeedForUser()
                        .map(TweetMapper::createMinimalTweetDTO));
    }
}
