package com.frederikzwartbol.springboottwitterrebuild.features.tweet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frederikzwartbol.springboottwitterrebuild.features.category.Category;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.hashtag.Hashtag;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.like.model.Like;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.models.children.Media;
import com.frederikzwartbol.springboottwitterrebuild.features.user.User;
import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Tweet Entity
 * Note: don't use @DATA annotation for performance reasons JPA & lombok
 * reference: https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/
 */
@Entity
@Table(name = "tweets")
@Builder
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tweet {
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
            (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId"
    )
    @JsonIgnoreProperties(value = {"tweets"})
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "tweet_hashtags",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id")
    )
    @JsonIgnoreProperties("tweets")
    @Builder.Default
    private Set<Hashtag> hashtags = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("tweets")
    @Builder.Default
    private Set<Like> likes = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"tweets","parentTweet", "replies"})
    @JoinColumn(name = "parentTweet_id")
    private Tweet parentTweet;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"tweets","parentTweet", "replies"})
    @Builder.Default
    private Set<Tweet> replies = new HashSet<>();

    @JsonIgnoreProperties({"tweets", "category"})
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    @OneToOne(mappedBy = "tweet", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Media media;

    /**
     * Derived Values:
     * Values are set with formula function when entity is loaded from the database
     */

    @Formula("(SELECT count(*) from likes l where l.tweet_id = id)")
    private long likeCount;
    @Formula("(SELECT count(*) from tweets_replies r where r.tweet_id = id)")
    private long replyCount;
    @Transient
    private long socialScore;

    @PostLoad
    public void calculateSocialScore(){
        this.socialScore = likeCount+(replyCount*2L);
    }

    public Tweet() {
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
