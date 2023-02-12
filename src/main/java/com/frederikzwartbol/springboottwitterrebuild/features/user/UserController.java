package com.frederikzwartbol.springboottwitterrebuild.features.user;

import com.frederikzwartbol.springboottwitterrebuild.features.user.models.dto.UserDTO;
import com.frederikzwartbol.springboottwitterrebuild.features.user.models.dto.UserMinimalDTO;
import com.frederikzwartbol.springboottwitterrebuild.features.user.models.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class UserController implements UserOperations {

    private final UserService userService;

    @Override
    public ResponseEntity<List<UserMinimalDTO>> findAllUsers() {
        return new ResponseEntity<>(userService.findAll()
                .stream()
                .map(UserMapper::entityToMinimalDTO)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest) {
        if (userService.existsByEmailAddress(userRequest.getEmailAddress())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                        UserMapper.entityToDTO(
                                userService.saveUserFromRequest(userRequest)),
                                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDTO> findUserById(Long userId) {
        return new ResponseEntity<>(
                        UserMapper.entityToDTO(
                                userService.findUserById(userId)),
                                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> findUserByHandle(String twitterHandle) {
        return new ResponseEntity<>(
                        UserMapper.entityToDTO(
                                userService.findUserByHandle(twitterHandle)),
                                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(UserRequest request) {
        return new ResponseEntity<>(
                        UserMapper.entityToDTO(
                                userService.updateUser(request)),
                                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteUserById(Long userId) {
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }

    @Override
    public ResponseEntity<?> followUser(Long userId, Long followUserId) {
        return ResponseEntity.ok(userService.followUser(userId, followUserId));
    }

    @Override
    public ResponseEntity<?> unfollowUser(Long userId, Long unfollowUserId) {
        return ResponseEntity.ok(userService.unfollowUser(userId, unfollowUserId));
    }

}
