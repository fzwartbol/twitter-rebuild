package com.frederikzwartbol.springboottwitterrebuild.features.tweet.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frederikzwartbol.springboottwitterrebuild.features.category.Category;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.hashtag.Hashtag;
import com.frederikzwartbol.springboottwitterrebuild.features.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Minimal tweet DTO for optimized loading
 * id,
 * message,
 * locationdate,
 * user
 */

@Data
@NoArgsConstructor
public class TweetMinimalDTO {
    private Long id;
    private String message;
    private LocalDateTime publicationDate;
    @JsonIgnoreProperties({"tweets", "followers", "following", "profileInformation"})
    private User user;
    @JsonIgnoreProperties("tweets")
    private Set<Hashtag> hashtags = new HashSet<>();
    @JsonIgnoreProperties("tweets")
    private Category category;
    @JsonIgnoreProperties({"parentTweet", "replies"})
    private TweetMinimalDTO parentTweet;
    private long likeCount;
    private long replyCount;
    private long socialScore;
    private MetaTweetResponseDTO metaData;
}


