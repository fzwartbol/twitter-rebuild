package com.frederikzwartbol.springbootjpamanytomany.controller.trending;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RequestMapping(TrendingOperations.PREFIX )
public interface TrendingOperations {
    String PREFIX="/trending";

    @GetMapping("/hot")
    ResponseEntity<?> getTopTenHashtag(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
            );

    @GetMapping("/replied")
    ResponseEntity<?> getTopRepliedTweets(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy);

    @GetMapping("/liked")
    ResponseEntity<?> getTopLikedTweets(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy);

    @GetMapping("/category")
    ResponseEntity<?> getTopByCategory (
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy);
}
