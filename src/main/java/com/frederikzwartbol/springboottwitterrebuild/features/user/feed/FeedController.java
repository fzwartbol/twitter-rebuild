package com.frederikzwartbol.springboottwitterrebuild.features.user.feed;

import com.frederikzwartbol.springboottwitterrebuild.aspect.UserScope;
import com.frederikzwartbol.springboottwitterrebuild.util.mapper.TweetMapper;
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
    @Override
    @UserScope
    public ResponseEntity<?> findFeedOfUser(Long userId, Optional<Integer> page, Optional<String> sortBy) {
        log.debug("trigger");
        return ResponseEntity.ok(
                feedService.findFeedForUser(userId,page,sortBy)
                .map(TweetMapper::createMinimalTweetDTO));
    }
}
