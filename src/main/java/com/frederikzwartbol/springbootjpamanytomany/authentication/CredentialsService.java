package com.frederikzwartbol.springbootjpamanytomany.authentication;

import com.frederikzwartbol.springbootjpamanytomany.authentication.entity.Credentials;
import com.frederikzwartbol.springbootjpamanytomany.authentication.entity.Role;
import com.frederikzwartbol.springbootjpamanytomany.authentication.repository.CredentialsRepository;
import com.frederikzwartbol.springbootjpamanytomany.authentication.repository.RoleRepository;
import com.frederikzwartbol.springbootjpamanytomany.exceptions.GenericNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CredentialsService implements UserDetailsService {

    private final CredentialsRepository credentialsRepository;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;

    /** Returns UserDetails of SpringSecurity class if exists */
    @Override
    public UserDetails loadUserByUsername(String username) {
        Credentials credentials = credentialsRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found in the database"));
        log.info("User found in the database {}", credentials);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        credentials.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole())));
        return new User(credentials.getUsername(),credentials.getPassword(),authorities);
    }

    public Credentials saveCredentials(Credentials user) {
        log.info("Saving new user to the database {}",user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return credentialsRepository.save(user);
    }

    public Role saveRole(Role role) {
        log.info("Saving new roleto the database {}",role);
        return roleRepo.save(role);
    }

    public Credentials addRoleToCredentials(String username, String roleName) {
        Credentials user = credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found in the database"));
        Role role = roleRepo.findByRole(roleName)
                .orElseThrow(() -> new GenericNotFoundException("Role not found in the database"));
        user.getRoles().add(role);
        log.info("adding role to user {}",user);
        return credentialsRepository.save(user);
    }

    public Credentials getCredentials(String username) {
        log.info("get credentials by name");
        return credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found in the database"));
    }


}
