package com.frederikzwartbol.springboottwitterrebuild.util.mapper;

import com.frederikzwartbol.springboottwitterrebuild.features.user.models.ProfileInformation;
import com.frederikzwartbol.springboottwitterrebuild.features.user.models.dto.UserDTO;
import com.frederikzwartbol.springboottwitterrebuild.features.user.models.dto.UserMinimalDTO;
import com.frederikzwartbol.springboottwitterrebuild.features.user.User;
import com.frederikzwartbol.springboottwitterrebuild.features.user.models.dto.UserRequest;

public class UserMapper {
    public static UserMinimalDTO entityToMinimalDTO(User user) {
        return new UserMinimalDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getTwitterHandle(),
                user.getProfileInformation(),
                user.getProfileImage());
    }

    public static UserDTO entityToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getTwitterHandle(),
                user.getProfileInformation(),
                user.getProfileImage(),
                user.getTweets(),
                user.getFollowers(),user.getFollowing());
    }

    public static User requestToUser(UserRequest request) {
       var profileInformation = profileInformationFromUserRequest(request);
       var user =  User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .twitterHandle(request.getTwitterHandle())
                .profileImage(request.getProfileImage())
                .profileInformation(profileInformation)
                .twitterHandle(request.getTwitterHandle())
                .build();

       profileInformation.setUser(user);
        return user;
    }

    public static ProfileInformation profileInformationFromUserRequest (UserRequest userRequest) {
        return new ProfileInformation(
                userRequest.getProfileHeader(),
                userRequest.getProfileBio(),
                userRequest.getProfileBackground(),
                userRequest.getLocation(),
                userRequest.getLinkUrl());
    }

}
