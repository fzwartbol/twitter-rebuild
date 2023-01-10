package com.frederikzwartbol.springbootjpamanytomany.models.DTO.tweet;

import com.frederikzwartbol.springbootjpamanytomany.models.DTO.MetaTweetResponseDTO;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.Category;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.Hashtag;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.Like;

import com.frederikzwartbol.springbootjpamanytomany.models.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class TweetDTO implements Serializable {
    private  Long id;
    private  String message;
    private LocalDateTime publicationDate;
    @JsonIgnoreProperties("tweets")
    private User user;
    @JsonIgnoreProperties("tweets")
    private Set<Hashtag> hashtags = new HashSet<>();
    @JsonIgnoreProperties("tweets")
    private Category category;
    @JsonIgnoreProperties("tweets")
    private  Set<Like> likes = new HashSet<>();
    @JsonIgnoreProperties({"parentTweet","replies"})
    private TweetMinimalDTO parentTweet;
    @JsonIgnoreProperties({"parentTweet","replies"})
    private Set<TweetDTO> replies = new HashSet<>();
    private long likeCount;
    private long replyCount;
    private MetaTweetResponseDTO metaData;

}
