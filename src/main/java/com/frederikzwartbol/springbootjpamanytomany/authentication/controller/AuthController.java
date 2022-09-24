package com.frederikzwartbol.springbootjpamanytomany.authentication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frederikzwartbol.springbootjpamanytomany.authentication.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@RestController
@Slf4j
public class AuthController implements AuthOperations {

    @Override
    public ResponseEntity<?> authenticateRefreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.getHeaderNames().asIterator().forEachRemaining(e -> log.info(e));

        Map<String, String> authTokens = Map.of(
                "accessToken", TokenService.createAccessToken(new User("test","password",new ArrayList<>()))
                );
        new ObjectMapper().writeValue(response.getOutputStream(), authTokens);
        return new ResponseEntity<>(authTokens, HttpStatus.OK);



    }
}
