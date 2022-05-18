package com.example.springbootjpamanytomany.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tweets")
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

    private String title;
    private String message;
    private LocalDateTime publicationDate;

    @ManyToOne
            (cascade = CascadeType.ALL)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId"
    )
    @JsonIgnoreProperties("tweets")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id")
    )
    @JsonIgnoreProperties("tweets")
    private Set<Hashtag> hashtags = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "tweet_likes",
            joinColumns = @JoinColumn(name = "tweet_id"),
            inverseJoinColumns = @JoinColumn(name = "like_id")
    )
    @JsonIgnoreProperties("tweets")
    private Set<Like> likes = new HashSet<>();

    public Tweet(String title, String message, LocalDateTime publicationDate) {
        this.title = title;
        this.message = message;
        this.publicationDate = publicationDate;
    }

    public Tweet() {
    }

    public Tweet(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(Set<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }
}
