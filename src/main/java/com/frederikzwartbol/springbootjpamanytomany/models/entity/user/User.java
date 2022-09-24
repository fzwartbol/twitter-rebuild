package com.frederikzwartbol.springbootjpamanytomany.models.entity.user;

import com.frederikzwartbol.springbootjpamanytomany.authentication.entity.Credentials;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.Category;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.message.Message;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.Tweet;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name="`user`")
@Entity
@Builder
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class User implements Serializable {

    @SequenceGenerator( name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Id
    @Column(name = "userId")
    private Long id;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ProfileInformation profileInformation;

    private String firstName;
    private String lastName;
    private String twitterHandle;
    private String profileImage;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"followers","following","tweets"})
    private Set<Tweet> tweets = new HashSet<>();

    @ManyToMany( cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"followers","following","tweets"})
    private Set<Category> interestedCategories = new HashSet<>();

    /**
     * Followers implementation
     */

    @JoinTable(name = "followers",
            joinColumns = {@JoinColumn(name = "follower_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @ManyToMany( cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"followers","following","tweets"})
    @ToString.Exclude
    private Set<User> followers;

    @JoinTable(name = "followers",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "follower_id")})
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnoreProperties(value = {"followers","following","tweets"})
    private Set<User> following;

    /**
     * Messages implementation
     * */

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"followers","following","sendMessages"})
    private Set<Message> sendMessages = new HashSet<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"followers","following","tweets"})
    private Set<Message> receivedMessages = new HashSet<>();

    /**
     * Authentication implementation
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "credentials_id", referencedColumnName = "id")
    @JsonIgnore
    private Credentials credentials;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(twitterHandle, user.twitterHandle) && Objects.equals(profileImage, user.profileImage) && Objects.equals(tweets, user.tweets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName,  twitterHandle, profileImage, tweets);
    }

}
