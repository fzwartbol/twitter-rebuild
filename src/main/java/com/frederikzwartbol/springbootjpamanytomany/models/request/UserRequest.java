package com.frederikzwartbol.springbootjpamanytomany.models.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Data
@RequiredArgsConstructor
public class UserRequest {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final String twitterHandle;
    private final Optional<String> profileImage;
    private final String username;
    private final String password;
}
