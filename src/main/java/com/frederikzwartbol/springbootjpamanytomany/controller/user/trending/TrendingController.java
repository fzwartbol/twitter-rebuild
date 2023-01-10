package com.frederikzwartbol.springbootjpamanytomany.controller.user.trending;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TrendingController implements TrendingOperations {

    @Override
    public ResponseEntity<?> getTrends() {
        return null;
    }
}

// get userid
// find interested categories for user
// top hashtags of interested categories
// top hashtags of location

//