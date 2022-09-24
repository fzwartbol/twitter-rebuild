package com.frederikzwartbol.springbootjpamanytomany.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.Tweet;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Table(name="`Like`")
@Entity
@Getter @Setter @ToString @RequiredArgsConstructor
public class Like implements Serializable {
    @SequenceGenerator( name = "like_sequence",
            sequenceName = "like_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "like_sequence"
    )
    @Id
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "like_id")
    )
    @JsonIgnoreProperties("likes")
    private Tweet tweet;
    private Long userId;
    private LocalDateTime likedDate;
    private boolean active;

    public Like(Long userId, LocalDateTime likedDate) {
        this.userId = userId;
        this.likedDate = likedDate;
        active=true;
    }

}
