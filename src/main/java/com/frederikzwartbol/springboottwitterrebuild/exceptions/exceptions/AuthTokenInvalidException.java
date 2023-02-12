package com.frederikzwartbol.springboottwitterrebuild.exceptions.exceptions;

public class AuthTokenInvalidException extends RuntimeException {
    public AuthTokenInvalidException(String message) {
        super(message);
    }
}
