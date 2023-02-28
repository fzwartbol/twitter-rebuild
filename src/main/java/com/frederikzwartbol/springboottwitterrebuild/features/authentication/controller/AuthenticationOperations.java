package com.frederikzwartbol.springboottwitterrebuild.features.authentication.controller;


import com.frederikzwartbol.springboottwitterrebuild.features.authentication.models.AuthResponseDto;
import com.frederikzwartbol.springboottwitterrebuild.features.authentication.SecurityConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;


@RequestMapping(AuthenticationOperations.PREFIX)
public interface AuthenticationOperations {
    String PREFIX = "/authenticate";

    @PostMapping
    ResponseEntity<AuthResponseDto> login (@RequestParam (name = SecurityConstants.USERNAME_REQUEST_PARAMETER) String userName,
                                           @RequestParam (name = SecurityConstants.PASSWORD_REQUEST_PARAMETER) String passWord
                                            );

    @PostMapping("/refresh")
    ResponseEntity<?> authenticateRefreshToken( @RequestParam (name = SecurityConstants.REFRESH_TOKEN_REQUEST_PARAMETER) String refresh_token) throws IOException;
}
