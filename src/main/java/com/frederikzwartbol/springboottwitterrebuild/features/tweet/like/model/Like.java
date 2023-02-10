package com.frederikzwartbol.springboottwitterrebuild.features.tweet.like.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.Tweet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;


@Table(name = "`Like`")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Like {
    @SequenceGenerator(name = "like_sequence",
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
        active = true;
    }

}
