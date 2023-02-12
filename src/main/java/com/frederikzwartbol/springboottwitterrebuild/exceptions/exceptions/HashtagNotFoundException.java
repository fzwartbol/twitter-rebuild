package com.frederikzwartbol.springboottwitterrebuild.exceptions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HashtagNotFoundException extends RuntimeException {
    public HashtagNotFoundException(String message) {
        super(message);
    }
}
