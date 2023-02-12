package com.frederikzwartbol.springboottwitterrebuild.features.authentication.controller;

import com.frederikzwartbol.springboottwitterrebuild.features.user.models.dto.UserRequest;
import com.frederikzwartbol.springboottwitterrebuild.features.user.UserService;
import com.frederikzwartbol.springboottwitterrebuild.features.user.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegisterController implements RegisterOperations {
    private final UserService userService;

    public ResponseEntity<?> register(@RequestBody UserRequest userRequest) {

        if (userService.existsByEmailAddress(userRequest.getEmailAddress())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                UserMapper.entityToDTO(
                        userService.saveUserFromRequest(userRequest)),
                        HttpStatus.CREATED);
    }
}
