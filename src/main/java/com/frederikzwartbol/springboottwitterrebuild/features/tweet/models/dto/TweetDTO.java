package com.frederikzwartbol.springboottwitterrebuild.features.tweet.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frederikzwartbol.springboottwitterrebuild.features.category.Category;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.hashtag.Hashtag;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.like.model.Like;
import com.frederikzwartbol.springboottwitterrebuild.features.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class TweetDTO {
    private Long id;
    private String message;
    private LocalDateTime publicationDate;
    @JsonIgnoreProperties("tweets")
    private User user;
    @JsonIgnoreProperties("tweets")
    private Set<Hashtag> hashtags = new HashSet<>();
    @JsonIgnoreProperties("tweets")
    private Category category;
    @JsonIgnoreProperties("tweets")
    private Set<Like> likes = new HashSet<>();
    @JsonIgnoreProperties({"parentTweet", "replies"})
    private TweetMinimalDTO parentTweet;
    @JsonIgnoreProperties({"parentTweet", "replies"})
    private Set<TweetDTO> replies = new HashSet<>();
    private long likeCount;
    private long replyCount;
    private long socialScore;
    private MetaTweetResponseDTO metaData;
}
