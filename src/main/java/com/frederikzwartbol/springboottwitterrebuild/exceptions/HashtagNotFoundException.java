package com.frederikzwartbol.springboottwitterrebuild.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class HashtagNotFoundException extends RuntimeException {
    public HashtagNotFoundException(String message) {
        super(message);
    }
}
