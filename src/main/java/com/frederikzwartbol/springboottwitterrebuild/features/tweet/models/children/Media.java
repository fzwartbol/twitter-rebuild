package com.frederikzwartbol.springboottwitterrebuild.features.tweet.models.children;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.Tweet;
import lombok.*;

import javax.persistence.*;

@Table(name = "`media`")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Media {
    @Id
    @Column(name = "tweet_id")
    @JsonIgnore
    private Long id;
    private String url;
    private MediaTypeFormat type;
    @OneToOne
    @MapsId
    @JoinColumn(name = "tweet_Id")
    @JsonIgnore
    private Tweet tweet;
}
