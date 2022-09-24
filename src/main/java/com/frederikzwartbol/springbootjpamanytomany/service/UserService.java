package com.frederikzwartbol.springbootjpamanytomany.service;

import com.frederikzwartbol.springbootjpamanytomany.authentication.CredentialsService;
import com.frederikzwartbol.springbootjpamanytomany.authentication.entity.Credentials;
import com.frederikzwartbol.springbootjpamanytomany.exceptions.UserNotFoundException;
import com.frederikzwartbol.springbootjpamanytomany.models.entity.user.User;
import com.frederikzwartbol.springbootjpamanytomany.models.request.UserRequest;
import com.frederikzwartbol.springbootjpamanytomany.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private static String STANDARD_PROFILE_IMAGE = "hallo";

    private final UserRepository userRepository;
    private final CredentialsService credentialsService;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not Found"));
    }

    public User saveUserFromRequest(UserRequest request) {
        var credentials = saveCredentials(request.getUsername(), request.getPassword());
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .credentials(credentials)
//                .profileImage(Optional.ofNullable(request.getProfileImage()).orElse(STANDARD_PROFILE_IMAGE))
                .twitterHandle(request.getTwitterHandle())
                .build();
        userRepository.save(user);
        log.info("User saved {}",user);
        return user;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    private Credentials saveCredentials (String username, String password) {
        return credentialsService.saveCredentials(new Credentials(username, password));
    }

    public User updateUser(UserRequest request) {
        User user = User.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
        userRepository.save(user);
        return user;
    }

    public boolean deleteUserById(Long userId) {
        userRepository.deleteById(userId);
        return true;
    }

    public User findUserByHandle(String twitterHandle) {
        return userRepository.findUserByTwitterHandle(twitterHandle)
                .orElseThrow(() -> new UserNotFoundException("User not Found"));
    }

    public User followUser(Long userId, Long followUserId) {
        User user = findUserById(userId);
        user.getFollowing().add(findUserById(followUserId));
        return userRepository.save(user);
    }

    public User unfollowUser(Long userId, Long unfollowUserId ) {
        User user = findUserById(userId);
        user.getFollowing().remove(findUserById(unfollowUserId));
        return userRepository.save(user);
    }
}