package com.frederikzwartbol.springbootjpamanytomany.models.DTO.user;

import com.frederikzwartbol.springbootjpamanytomany.models.entity.tweet.Tweet;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
public class UserDTO implements Serializable {
    private final Long userId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final String twitterHandle;
    private final String profileImage;
    private final String profileBackground;
    @JsonIgnoreProperties("user")
    private Set<Tweet> tweets;
    private Set<User> followers;
    @JsonIgnoreProperties(value = {"followers","following"})
    private Set<User> following;
}
