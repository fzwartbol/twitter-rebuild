package com.frederikzwartbol.springboottwitterrebuild.features.user.models.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserRequest {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final String password;
    private final String twitterHandle;
    private final String profileImage;
    private final String profileHeader;
    private final String profileBio;
    private final String profileBackground;
    private final String location;
    private final String linkUrl;
}
