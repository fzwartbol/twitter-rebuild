package com.frederikzwartbol.springboottwitterrebuild.features.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.frederikzwartbol.springboottwitterrebuild.features.authentication.models.AuthResponseDto;
import com.frederikzwartbol.springboottwitterrebuild.features.authentication.models.UserTokenDto;
import com.frederikzwartbol.springboottwitterrebuild.features.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {
    @Value("${app.secret}")
    public String algorithmHashSecret = "secret";
    private final Algorithm algorithm = Algorithm.HMAC256(algorithmHashSecret.getBytes());

    private final CustomUserDetailsService customUserDetailsService;


    public String createAccessToken(UserDetails user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.ACCESS_TOKEN_EXPIRY_TIME_MIN *  1000L))
                .withClaim("roles", user.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(algorithm);
    }

    public String createRefreshToken(UserDetails user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.REFRESH_TOKEN_EXPIRY_TIME_MIN * 1000L))
                .withClaim("roles", user.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(algorithm);
    }

    public  JWTVerifier getVerifier() {
        return JWT.require(algorithm).build();
    }

    public DecodedJWT decodeJWT(String token) {
        return getVerifier().verify(token);
    }
    public String getUserNameFromJWTToken (String token) {
        return decodeJWT(token).getSubject();
    }
    public boolean validateToken(String token) {
        try { getVerifier().verify(token);
            return true;
        } catch (Exception exception) {
            return  false;
        }
    }

    public AuthResponseDto createAuthResponseDTO(User user) {
        return new AuthResponseDto(
                createAccessToken(customUserDetailsService.loadUserByUsername(user.getCredentials().getUsername())),
                SecurityConstants.ACCESS_TOKEN_EXPIRY_TIME_MIN,
                SecurityConstants.REFRESH_TOKEN_EXPIRY_TIME_MIN,
                createRefreshToken(customUserDetailsService.loadUserByUsername(user.getCredentials().getUsername())),
                SecurityConstants.TOKEN_TYPE,
                new UserTokenDto(user.getId(),user.getTwitterHandle(),user.getCredentials().getUsername(),user.getProfileImage())
                );
    }

}
