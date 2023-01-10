package com.frederikzwartbol.springbootjpamanytomany.controller.trending;


import com.frederikzwartbol.springbootjpamanytomany.repository.TweetRepository;
import com.frederikzwartbol.springbootjpamanytomany.service.TrendingGlobalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TrendingGlobalController implements TrendingGlobalOperations {

    private final TrendingGlobalService trendingService;
    private final TweetRepository tweetRepository;

    @Override
    public ResponseEntity<?> getTopTenHashtag(Optional<Integer> page, Optional<String> sortBy) {
        return ResponseEntity.ok(trendingService.getTopHashtags());
    }

    @Override
    public ResponseEntity<?> getTopRepliedTweets(Optional<Integer> page, Optional<String> sortBy) {
        return ResponseEntity.ok(trendingService.getTopRepliedTweets());
    }

    @Override
    public ResponseEntity<?> getTopLikedTweets(Optional<Integer> page, Optional<String> sortBy) {
        return ResponseEntity.ok(trendingService.getTopLikedTweets());
    }

    @Override
    public ResponseEntity<?> getTopByCategory(Optional<Integer> page, Optional<String> sortBy) {
        return ResponseEntity.ok(trendingService.getTopByCategory(page,sortBy));
    }

}
