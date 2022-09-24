package com.frederikzwartbol.springbootjpamanytomany.authentication.filters;


import com.frederikzwartbol.springbootjpamanytomany.authentication.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("Login attempt: Username {}, Password is: {}",request.getParameter("username"),request.getParameter("password"));
        return authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getParameter("username"),
                                request.getParameter("password")));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        response.setContentType(APPLICATION_JSON_VALUE);
        User userdetails = (User)authResult.getPrincipal();
        log.info("Succesvol auth {}",userdetails);

        Map<String, String> authTokens = Map.of(
                "accessToken",TokenService.createAccessToken(userdetails));
        new ObjectMapper().writeValue(response.getOutputStream(), authTokens);

        ResponseCookie refreshCookie = ResponseCookie.from("access-http-only-cookie", TokenService.createRefreshToken(userdetails))
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60)
                .domain("twitter.com")
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE,refreshCookie.toString());
    }

}
