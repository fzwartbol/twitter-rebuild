package com.example.springbootjpamanytomany.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserRequest {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
}
