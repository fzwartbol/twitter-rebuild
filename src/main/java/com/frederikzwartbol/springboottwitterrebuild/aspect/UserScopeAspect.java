package com.frederikzwartbol.springboottwitterrebuild.aspect;

import com.frederikzwartbol.springboottwitterrebuild.features.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class UserScopeAspect {
    private final UserRepository userRepository;

    @Before("@annotation(UserScope)")
    public void checkUserAccess(JoinPoint joinPoint) {
//        log.info("UserScope aspect triggered on jointpoint: {}",joinPoint.getSignature());
//        Object[] args = joinPoint.getArgs();
//
//        Optional<User> optUser = userRepository.findById((Long) args[0]);
//        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        String requestedUserName = optUser.map( user -> user.getCredentials().getUsername()).orElse(null);
//        if(optUser.isEmpty()||!currentUser.equals(requestedUserName)) {
//            throw new AccessDeniedException("Access denied for user: " + currentUser);
//        }
    }


}
