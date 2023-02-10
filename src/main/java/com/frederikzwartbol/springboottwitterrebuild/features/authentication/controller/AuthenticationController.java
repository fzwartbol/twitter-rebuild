package com.frederikzwartbol.springboottwitterrebuild.features.authentication.controller;

import com.frederikzwartbol.springboottwitterrebuild.features.authentication.TokenService;
import com.frederikzwartbol.springboottwitterrebuild.features.authentication.models.AuthResponseDto;
import com.frederikzwartbol.springboottwitterrebuild.features.authentication.SecurityConstants;
import com.frederikzwartbol.springboottwitterrebuild.features.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController implements AuthenticationOperations {
    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authManager;

    @Override
    public ResponseEntity<AuthResponseDto> login(@RequestParam(name = SecurityConstants.USERNAME_REQUEST_PARAMETER) String username,
                                                 @RequestParam (name = SecurityConstants.PASSWORD_REQUEST_PARAMETER) String password) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        var authResponseDTO = tokenService.createAuthResponseDTO(userService.findUserByEmailAddress(authentication.getName()));

        return new ResponseEntity<>(authResponseDTO,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> authenticateRefreshToken(@RequestParam (name = SecurityConstants.REFRESH_TOKEN_REQUEST_PARAMETER) String refresh_token) {
        if (refresh_token == null) {
            log.debug("Authentication of refreshToken failed: Token not found in requestBody.");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        var userName = tokenService.getUserNameFromJWTToken(refresh_token);

        var authResponseDTO = tokenService
                .createAuthResponseDTO(userService.findUserByEmailAddress(userName));

        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }
}
