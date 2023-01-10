package com.frederikzwartbol.springbootjpamanytomany.controller.search;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RequestMapping(SearchOperation.PREFIX)
public interface SearchOperation {
    String PREFIX = "/search";

    @GetMapping("")
    ResponseEntity<?> searchTwitter(
            @RequestParam String q,
            @RequestParam(defaultValue = "tweet") String f,
            @RequestParam(defaultValue = "typed_query") String src,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy);
}
