package com.frederikzwartbol.springboottwitterrebuild.features.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frederikzwartbol.springboottwitterrebuild.features.authentication.Credentials;
import com.frederikzwartbol.springboottwitterrebuild.features.conversation.Conversation;
import com.frederikzwartbol.springboottwitterrebuild.features.user.models.ProfileInformation;
import com.frederikzwartbol.springboottwitterrebuild.features.category.Category;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.Tweet;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "`user`")
@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @SequenceGenerator(name = "user_sequence",
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
    @Column(unique = true)
    private String twitterHandle;
    private String profileImage;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"followers", "following", "tweets"})
    private Set<Tweet> tweets = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"followers", "following", "tweets"})
    private Set<Category> interestedCategories = new HashSet<>();

    /**
     * Followers implementation
     */

    @JoinTable(name = "followers",
            joinColumns = {@JoinColumn(name = "follower_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"followers", "following", "tweets"})
    @ToString.Exclude
    private Set<User> followers;

    @JoinTable(name = "followers",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "follower_id")})
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnoreProperties(value = {"followers", "following", "tweets"})
    private Set<User> following;

    /**
     * Messages implementation
     */
    @JoinTable(name = "User_conversations",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "conversation_id")})
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Conversation> conversations;

    public void addConversation (Conversation conversation) {
        conversations.add(conversation);
    }

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
        return Objects.hash(id, firstName, lastName, twitterHandle, profileImage, tweets);
    }

}
