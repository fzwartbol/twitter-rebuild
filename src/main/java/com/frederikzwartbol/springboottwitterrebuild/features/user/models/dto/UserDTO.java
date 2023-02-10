package com.frederikzwartbol.springboottwitterrebuild.features.user.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.frederikzwartbol.springboottwitterrebuild.features.user.User;
import com.frederikzwartbol.springboottwitterrebuild.features.user.models.ProfileInformation;
import com.frederikzwartbol.springboottwitterrebuild.features.tweet.Tweet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;


@Data
@Builder
@AllArgsConstructor
public class UserDTO {
    private final Long userId;
    private final String firstName;
    private final String lastName;
    private final String twitterHandle;
    private final ProfileInformation profileInformation;
    private final String profileImage;
    @JsonIgnoreProperties("user")
    private Set<Tweet> tweets;
    private Set<User> followers;
    @JsonIgnoreProperties(value = {"followers", "following"})
    private Set<User> following;
}
