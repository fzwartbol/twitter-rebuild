package com.frederikzwartbol.springboottwitterrebuild.features.user.feed;

import com.frederikzwartbol.springboottwitterrebuild.aspect.UserScope;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.TweetMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FeedController implements FeedOperations {
    private final FeedService feedService;
    @UserScope
    @Override
    public ResponseEntity<?> findFeedOfUser(Long userId, Optional<Integer> page, Optional<String> sortBy) {
        return ResponseEntity.ok(
                feedService.findFeedForUser(userId,page,sortBy)
                .map(TweetMapper::createMinimalTweetDTO));
    }
}
