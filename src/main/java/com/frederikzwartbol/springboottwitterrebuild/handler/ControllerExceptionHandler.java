package com.frederikzwartbol.springboottwitterrebuild.handler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.frederikzwartbol.springboottwitterrebuild.exceptions.AuthTokenInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({AuthTokenInvalidException.class, JWTVerificationException.class})
    public ResponseEntity<?> tokenVerificationException(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({TokenExpiredException.class})
    public ResponseEntity<?>  tokenVerificationExpired(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(),  HttpStatus.FORBIDDEN);
    }
}

