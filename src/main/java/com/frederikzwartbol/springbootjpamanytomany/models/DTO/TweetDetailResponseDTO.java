package com.frederikzwartbol.springbootjpamanytomany.models.DTO;

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
public class TweetDetailResponseDTO implements Serializable {
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
    private TweetMinimalResponseDTO parentTweet;
    @JsonIgnoreProperties({"parentTweet","replies"})
    private Set<TweetDetailResponseDTO> replies = new HashSet<>();
    private long likeCount;
    private long replyCount;
    private MetaTweetResponseDTO metaData;

}
