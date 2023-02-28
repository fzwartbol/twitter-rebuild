package com.frederikzwartbol.springboottwitterrebuild.features.user;

import com.frederikzwartbol.springboottwitterrebuild.features.authentication.CustomUserDetailsService;
import com.frederikzwartbol.springboottwitterrebuild.features.authentication.Credentials;
import com.frederikzwartbol.springboottwitterrebuild.exceptions.exceptions.UserNotFoundException;
import com.frederikzwartbol.springboottwitterrebuild.features.user.models.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not Found"));
    }

    public User findUserByEmailAddress(String emailAddress) {
        return userRepository.findUserByCredentials_username(emailAddress)
                .orElseThrow(() -> new UserNotFoundException("User not Found"));
    }

    public boolean existsByEmailAddress(String emailAddress) {
        return  userRepository.existsByCredentials_username(emailAddress);
    }

    public User saveUserFromRequest(UserRequest request) {
        var credentials = saveCredentials(request.getEmailAddress(), request.getPassword());

        User user = UserMapper.requestToUser(request);
        user.setCredentials(credentials);
        userRepository.save(user);

        log.info("User saved {}", user);
        return user;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    private Credentials saveCredentials(String username, String password) {
        return customUserDetailsService.saveCredentials(new Credentials(username, password));
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

    public User unfollowUser(Long userId, Long unfollowUserId) {
        User user = findUserById(userId);
        user.getFollowing().remove(findUserById(unfollowUserId));
        return userRepository.save(user);
    }
}

