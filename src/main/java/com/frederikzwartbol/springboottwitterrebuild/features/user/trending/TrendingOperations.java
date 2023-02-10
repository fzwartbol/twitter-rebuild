package com.frederikzwartbol.springboottwitterrebuild.features.user.trending;

import com.frederikzwartbol.springboottwitterrebuild.features.user.UserOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(UserOperations.PREFIX + "/trending")
public interface TrendingOperations {
    @GetMapping("")
    ResponseEntity<?> getTrends();
}
