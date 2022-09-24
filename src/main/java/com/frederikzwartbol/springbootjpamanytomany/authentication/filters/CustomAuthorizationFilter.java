package com.frederikzwartbol.springbootjpamanytomany.authentication.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.frederikzwartbol.springbootjpamanytomany.authentication.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/authenticate")) { filterChain.doFilter(request, response); } else {

            Optional<String> token = parseBearerHeader(request.getHeader(HttpHeaders.AUTHORIZATION));
            if (!token.isEmpty()) {
//            log.info("No token present");
//            filterChain.doFilter(request, response);
//        } else {
                try {
                    DecodedJWT decodedJWT = TokenService.decodeJWT(token.get());
                    setSecurityContextFromJWT(decodedJWT);
                } catch (Exception exception) {
                    log.error("Error {},", exception.getMessage());
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

    public Optional<String> parseBearerHeader(String authorizationHeader) {
        String parsedHeader = null;
        if (authorizationHeader != null&&authorizationHeader.startsWith("Bearer ")) {
            parsedHeader = authorizationHeader.substring("Bearer ".length());
        }
        return Optional.ofNullable(parsedHeader);
    }

    public void setSecurityContextFromJWT (DecodedJWT decodedJWT) {
        String username = decodedJWT.getSubject();
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, mapGrantedAuthorities(roles));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    public Collection<SimpleGrantedAuthority> mapGrantedAuthorities(String[] roles) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Arrays.stream(roles).forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });
        return authorities;
    }

}
