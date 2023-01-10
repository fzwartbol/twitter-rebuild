package com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.Category;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.Hashtag;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.Like;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * DON'T USE @DATA annotation for performance reasons
 * JPA WITH LOMBOK
 * https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/
 * */

@Entity
@Table(name = "tweets")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter @Setter @ToString @RequiredArgsConstructor
public class Tweet implements Serializable {
    @SequenceGenerator(name = "tweet_sequence",
            sequenceName = "tweet_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tweet_sequence"
    )
    @Id
    private Long id;
    private String message;
    private LocalDateTime publicationDate;

    @ManyToOne
            (cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId"
    )
    @JsonIgnoreProperties(value ={"tweets"})
    @ToString.Exclude
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "tweet_hashtags",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id")
    )
    @JsonIgnoreProperties("tweets")
    private Set<Hashtag> hashtags = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.LAZY)
    @JsonIgnoreProperties("tweets")
    private Set<Like> likes = new HashSet<>();

    @ManyToOne(cascade={CascadeType.ALL})
    @JsonIgnoreProperties({"parentTweet","replies"})
    @JoinColumn(name="parentTweet_id")
    private Tweet parentTweet;

    @OneToMany( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"parentTweet","replies"})
    private Set<Tweet> replies = new HashSet<>();

    @ManyToOne(cascade={CascadeType.ALL})
    private Category category;

    @OneToOne(mappedBy = "tweet", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Media media;

    /** Derived Values:
     * Values are set with formula function over entity is loaded from the database */
    @Formula("(SELECT count(*) from likes l where l.tweet_id = id)")
    private long likeCount;
    @Formula("(SELECT count(*) from tweets_replies r where r.tweet_id = id)")
    private long replyCount;

    public Tweet(String message, LocalDateTime publicationDate) {
        this.message = message;
        this.publicationDate = publicationDate;
    }
}
