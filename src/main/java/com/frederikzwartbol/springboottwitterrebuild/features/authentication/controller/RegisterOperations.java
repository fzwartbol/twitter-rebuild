package com.frederikzwartbol.springboottwitterrebuild.features.authentication.controller;

import com.frederikzwartbol.springboottwitterrebuild.features.user.models.dto.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface RegisterOperations {
    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody UserRequest userRequest);
}
