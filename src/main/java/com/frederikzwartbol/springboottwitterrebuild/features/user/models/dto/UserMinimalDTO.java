package com.frederikzwartbol.springboottwitterrebuild.features.user.models.dto;

import com.frederikzwartbol.springboottwitterrebuild.features.user.models.ProfileInformation;

public record UserMinimalDTO(
        Long userid,
        String firstName,
        String lastName,
        String twitterHandle,
        ProfileInformation profileInformation,
        String profileImage
) {
}
