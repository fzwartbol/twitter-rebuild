package com.frederikzwartbol.springboottwitterrebuild.features.tweet.hashtag;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.Tweet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Hashtag {
    @SequenceGenerator(name = "tag_sequence",
            sequenceName = "tag_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tag_sequence"
    )
    @Id
    private Long id;
    @Column(unique = true)
    private String message;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "hashtags")
    @JsonIgnoreProperties("hashtags")
    private Set<Tweet> tweets = new HashSet<>();
    private LocalDateTime firstCreated;

    public Hashtag(String message) {
        this.message = message;
    }

    public Hashtag(String message, LocalDateTime firstCreated) {
        this.message = message;
        this.firstCreated = firstCreated;
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
        return "Hashtag{" +
                "message='" + message + '\'' +
                ", firstCreated=" + firstCreated +
                '}';
    }
}
