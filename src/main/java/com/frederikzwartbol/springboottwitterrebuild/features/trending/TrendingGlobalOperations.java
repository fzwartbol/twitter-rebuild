package com.frederikzwartbol.springboottwitterrebuild.features.trending;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RequestMapping(TrendingGlobalOperations.PREFIX)
public interface TrendingGlobalOperations {
    String PREFIX = "/trending";

    @GetMapping("/hot")
    ResponseEntity<?> getTopTenHashtag();

    @GetMapping("/replied")
    ResponseEntity<?> getTopTenRepliedTweets();

    @GetMapping("/liked")
    ResponseEntity<?> getTopLikedTweets();

    @GetMapping("/category")
    ResponseEntity<?> getTopByCategory(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy);
}
