package com.frederikzwartbol.springboottwitterrebuild.features.search;

import com.frederikzwartbol.springboottwitterrebuild.features.tweet.models.dto.TweetMinimalDTO;
import com.frederikzwartbol.springboottwitterrebuild.features.user.models.dto.UserMinimalDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Data
@Builder
public class SearchAggregate {
    private String query;
    private String fieldType;
    private String src;
    private int page;
    private String sortBy;
    private LocalDateTime timestamp;
    private Page<TweetMinimalDTO> queryTweets;
    private Page<UserMinimalDTO> queryUsers;
}
