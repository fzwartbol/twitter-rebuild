package com.frederikzwartbol.springboottwitterrebuild.features.search;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class SearchController implements SearchOperation {
    private final SearchService searchService;

    /**
     * Example of search query Twitter
     * https://twitter.com/search?q=hallo&src=typed_query&f=user
     *
     * @param q
     * @param src
     * @param page
     * @param sortBy
     * @return
     */
    @Override
    public ResponseEntity<?> searchTwitter(String q, String f, String src, Optional<Integer> page, Optional<String> sortBy) {
        return ResponseEntity.ok(searchService.returnSearchQuery(q, f, src, page, sortBy));
    }
}
