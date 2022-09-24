package com.frederikzwartbol.springbootjpamanytomany.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Date;
import java.util.stream.Collectors;

public class TokenService {
    private static final Long ACCESSTOKEN_EXPIRY_TIME_MS = 18*60*1000L;
    private static final Long REFRESH_TOKEN_EXPIRY_TIME_MS = 30*60*1000L;

    @Value("${auth.secret}")
    private static  String SECRET = "secret";
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes());

    public static String createAccessToken(User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ ACCESSTOKEN_EXPIRY_TIME_MS))
                .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    public static String createRefreshToken(User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ REFRESH_TOKEN_EXPIRY_TIME_MS))
                .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    public static JWTVerifier getVerifier() {
        return JWT.require(algorithm).build();
    }

    public static DecodedJWT decodeJWT (String token) {
        return getVerifier().verify(token);
    }

}
