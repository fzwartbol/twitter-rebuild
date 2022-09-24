package com.frederikzwartbol.springbootjpamanytomany.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.Tweet;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Table(name="`category`")
@Entity
@Getter @Setter @ToString @RequiredArgsConstructor
public class Category implements Serializable {
    @SequenceGenerator( name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_sequence"
    )
    @Id
    @JsonIgnore
    private Long id;
    @Column(unique = true)
    private String categoryName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "hashtags")
    @JsonIgnoreProperties(value = {"category","followers","following","tweets"})
    private Set<Tweet> tweets = new HashSet<>();

    @ManyToMany( cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonIgnoreProperties(value = {"interestedCategories","followers","following","tweets"})
    private Set<User> interestedUsers = new HashSet<>();

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
