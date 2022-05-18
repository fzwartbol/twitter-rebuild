package com.example.springbootjpamanytomany.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;



@Table(name="`Like`")
@Entity
@Data
@NoArgsConstructor
public class Like {
    @SequenceGenerator( name = "like_sequence",
            sequenceName = "like_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "like_sequence"
    )
    @Id
    private Long likeId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "likes")
    @JsonIgnoreProperties("likes")
    private Set<Tweet> tweets = new HashSet<>();

    private Long userId;
    private LocalDateTime likedDate;

    public Like(Long userId, LocalDateTime likedDate) {
        this.userId = userId;
        this.likedDate = likedDate;
    }



}
