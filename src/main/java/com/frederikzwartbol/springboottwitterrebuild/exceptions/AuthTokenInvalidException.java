package com.frederikzwartbol.springboottwitterrebuild.exceptions;

public class AuthTokenInvalidException extends RuntimeException {
    public AuthTokenInvalidException(String message) {
        super(message);
    }
}
