package com.frederikzwartbol.springbootjpamanytomany.service.search;

import com.frederikzwartbol.springbootjpamanytomany.models.DTO.tweet.TweetMinimalDTO;
import com.frederikzwartbol.springbootjpamanytomany.models.DTO.user.UserMinimalDTO;
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
    private Page<UserMinimalDTO>  queryUsers;
}
