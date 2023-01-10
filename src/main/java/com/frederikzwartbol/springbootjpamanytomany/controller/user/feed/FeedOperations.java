package com.frederikzwartbol.springbootjpamanytomany.controller.user.feed;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping("")
public interface FeedOperations {
            @GetMapping("/user/{userId}/feed")
            ResponseEntity<?> findFeedOfUser(@PathVariable("userId") Long userId, Optional<Integer> page, Optional<String> sortBy);
}
