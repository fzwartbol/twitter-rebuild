package com.example.springbootjpamanytomany.service;

import com.example.springbootjpamanytomany.repository.UserRepository;
import com.example.springbootjpamanytomany.enitity.User;
import com.example.springbootjpamanytomany.exceptions.UserNotFoundException;
import com.example.springbootjpamanytomany.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserById(Long userId) {
    return userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not Found"));
    }

    public User saveUser(UserRequest request) {


//
//        User user = User.builder()
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .emailAddress(request.getEmailAddress())
//                .build();


        User user = new User(request.getFirstName(), request.getLastName(), request.getEmailAddress());
        userRepository.save(user);
        return user;
    }

    public User updateUser(UserRequest request) {
//        User user = User.builder()
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .emailAddress(request.getEmailAddress())
//                .build();
        User user = new User(request.getFirstName(), request.getLastName(), request.getEmailAddress());
        userRepository.save(user);
        return user;
    }

    public boolean deleteUserById (Long userId) {
        userRepository.deleteById(userId);
        return true;
    }

}
