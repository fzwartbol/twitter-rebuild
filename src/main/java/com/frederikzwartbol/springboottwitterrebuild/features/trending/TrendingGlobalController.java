package com.frederikzwartbol.springboottwitterrebuild.features.trending;


import com.frederikzwartbol.springboottwitterrebuild.features.tweet.Tweet;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TrendingGlobalController implements TrendingGlobalOperations {
    private final TrendingGlobalService trendingService;

    @Override
    public ResponseEntity<?> getTopTenHashtag() {
        return ResponseEntity.ok(trendingService.getTopHashtags());
    }
    @Override
    public ResponseEntity<?> getTopTenRepliedTweets() {
        return ResponseEntity.ok(trendingService.getTopRepliedTweets());
    }

    @Override
    public ResponseEntity<?> getTopLikedTweets() {
        return ResponseEntity.ok(trendingService.getTopLikedTweets());
    }

    @Override
    public ResponseEntity<List<Page<Tweet>>> getTopByCategory(Optional<Integer> page, Optional<String> sortBy) {
        return ResponseEntity.ok(trendingService.getTopByCategory(page, sortBy));
    }

}
