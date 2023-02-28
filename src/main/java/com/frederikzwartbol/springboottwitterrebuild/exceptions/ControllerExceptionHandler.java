package com.frederikzwartbol.springboottwitterrebuild.exceptions;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.frederikzwartbol.springboottwitterrebuild.exceptions.exceptions.AuthTokenInvalidException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({AuthTokenInvalidException.class, JWTVerificationException.class})
    public ResponseEntity<?> tokenVerificationException(RuntimeException exception, HttpRequest request) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({TokenExpiredException.class})
    public ResponseEntity<?>  tokenVerificationExpired(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(),  HttpStatus.FORBIDDEN);
    }
}

