package com.frederikzwartbol.springbootjpamanytomany.models.DTO.user;

import com.frederikzwartbol.springbootjpamanytomany.models.entity.user.ProfileInformation;

public record UserMinimalDTO(
        Long userid,
        String firstName,
        String lastName,
        String twitterHandle,
        ProfileInformation profileInformation,
        String profileImage
        ) {}
