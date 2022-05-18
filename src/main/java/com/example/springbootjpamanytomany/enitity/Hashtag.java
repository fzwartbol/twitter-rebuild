package com.example.springbootjpamanytomany.enitity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Hashtag {
    @SequenceGenerator( name = "tag_sequence",
            sequenceName = "tag_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tag_sequence"
    )
    @Id
    private Long id;
    private String message;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "hashtags")
    @JsonIgnoreProperties("hashtags")
    private Set<Tweet> tweets = new HashSet<>();


    public Hashtag(Long id, String message, Set<Tweet> tweets) {
        this.id = id;
        this.message = message;
        this.tweets = tweets;
    }

    public Hashtag(String message) {
        this.message = message;
    }

    public Hashtag() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(Set<Tweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hashtag hashtag = (Hashtag) o;
        return Objects.equals(id, hashtag.id) && Objects.equals(message, hashtag.message) && Objects.equals(tweets, hashtag.tweets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, tweets);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", posts=" + tweets +
                '}';
    }
}
