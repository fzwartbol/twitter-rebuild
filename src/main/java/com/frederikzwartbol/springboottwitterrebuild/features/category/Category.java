package com.frederikzwartbol.springboottwitterrebuild.features.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.Tweet;
import com.frederikzwartbol.springboottwitterrebuild.features.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Table(name = "`category`")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Category {
    @SequenceGenerator(name = "category_sequence",
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            mappedBy = "category")
    @JsonIgnoreProperties(value = {"category", "followers", "following", "tweets"})
    @ToString.Exclude
    private Set<Tweet> tweets = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonIgnoreProperties(value = {"interestedCategories", "followers", "following", "tweets"})
    private Set<User> interestedUsers = new HashSet<>();

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
