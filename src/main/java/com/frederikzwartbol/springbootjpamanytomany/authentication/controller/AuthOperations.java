package com.frederikzwartbol.springbootjpamanytomany.authentication.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping(AuthOperations.PREFIX)
public interface AuthOperations {
    String PREFIX = "/authenticate";

    @GetMapping("/refresh")
    ResponseEntity<?> authenticateRefreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
