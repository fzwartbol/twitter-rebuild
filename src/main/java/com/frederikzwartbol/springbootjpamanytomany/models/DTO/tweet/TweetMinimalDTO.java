package com.frederikzwartbol.springbootjpamanytomany.models.DTO.tweet;

import com.frederikzwartbol.springbootjpamanytomany.models.DTO.MetaTweetResponseDTO;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.Category;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.Hashtag;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/** Minimal tweet DTO for optimized loading
 * id,
 * message,
 * locationdate,
 * user
 * */

@Data
@NoArgsConstructor
public class TweetMinimalDTO implements Serializable {
    private  Long id;
    private  String message;
    private LocalDateTime publicationDate;
    @JsonIgnoreProperties({"tweets","followers","following","profileInformation"})
    private User user;
    @JsonIgnoreProperties("tweets")
    private Set<Hashtag> hashtags = new HashSet<>();
    @JsonIgnoreProperties("tweets")
    private Category category;
    @JsonIgnoreProperties({"parentTweet","replies"})
    private TweetMinimalDTO parentTweet;

    private long likeCount;
    private long replyCount;

    private MetaTweetResponseDTO metaData;
}


