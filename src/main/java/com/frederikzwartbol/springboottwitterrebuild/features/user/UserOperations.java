package com.frederikzwartbol.springboottwitterrebuild.features.user;

import com.frederikzwartbol.springboottwitterrebuild.features.user.models.dto.UserDTO;
import com.frederikzwartbol.springboottwitterrebuild.features.user.models.dto.UserMinimalDTO;
import com.frederikzwartbol.springboottwitterrebuild.features.user.models.dto.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(UserOperations.PREFIX)
public interface UserOperations {

    String PREFIX = "/users";

    @GetMapping
    ResponseEntity<List<UserMinimalDTO>> findAllUsers();

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    ResponseEntity<?> saveUser(@RequestBody UserRequest request);
    @GetMapping("/{userId}")
    ResponseEntity<UserDTO> findUserById(@PathVariable("userId") Long userId);

    @GetMapping("/handle/{twitterHandle}")
    ResponseEntity<UserDTO> findUserByHandle(@PathVariable("twitterHandle") String twitterHandle);

    @PutMapping("/{userId}")
    ResponseEntity<UserDTO> updateUser(@RequestBody UserRequest request);

    @DeleteMapping("/{userId}")
    ResponseEntity<?> deleteUserById(@PathVariable("userId") Long userId);

    @PostMapping("/{followUserId}/follow/{userId}")
    ResponseEntity<?> followUser(@PathVariable("userId") Long userId, @PathVariable("followUserId") Long followUserId);

    @PostMapping("/{followUserId}/unfollow/{userId}")
    ResponseEntity<?> unfollowUser(@PathVariable("userId") Long userId, @PathVariable("followUserId") Long unfollowUserId);

}
