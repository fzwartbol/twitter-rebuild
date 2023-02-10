package com.frederikzwartbol.springboottwitterrebuild.features.authentication.models;

public record AuthResponseDto(
        String access_token,
        Long expires_in,
        Long refresh_expires_in,
        String refresh_token,
        String token_type,
        UserTokenDto user
) {}
