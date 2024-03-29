package com.frederikzwartbol.springboottwitterrebuild.features.authentication;

import com.frederikzwartbol.springboottwitterrebuild.exceptions.exceptions.AuthenticationException;
import com.frederikzwartbol.springboottwitterrebuild.features.authentication.models.Role;
import com.frederikzwartbol.springboottwitterrebuild.features.authentication.repository.CredentialsRepository;
import com.frederikzwartbol.springboottwitterrebuild.features.authentication.repository.RoleRepository;
import com.frederikzwartbol.springboottwitterrebuild.exceptions.exceptions.GenericNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;


@RequiredArgsConstructor
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final CredentialsRepository credentialsRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    // for demonstration purposes
    @PostConstruct
    void init() {
        log.debug("Post construct method after CustomUserDetailsService bean creation.");
        roleRepository.save(new Role(null,"USER"));
        roleRepository.save(new Role(null,"ADMIN"));
    }

    @Override
    public User loadUserByUsername(String username) {
        var credentials = credentialsRepository.findByUsername(username).orElseThrow(() -> new AuthenticationException("User/Password not found in the database"));
        log.info("User found in the database {}", credentials);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        credentials.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole())));
        return new User(credentials.getUsername(), credentials.getPassword(), authorities);
    }

    public Credentials saveCredentials(Credentials credentials) {
        Role roles = roleRepository.findByRole("USER").get();
        credentials.setRoles(Set.of(roles));
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        log.info("Saving new credentials to the database {}", credentials);
        return credentialsRepository.save(credentials);
    }

    public Role saveRole(Role role) {
        log.info("Saving new role to the database {}", role);
        return roleRepository.save(role);
    }

    public Credentials addRoleToCredentials(String username, String roleName) {
        Credentials userDetails = credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found in the database"));
        Role role = roleRepository.findByRole(roleName)
                .orElseThrow(() -> new GenericNotFoundException("Role not found in the database"));
        userDetails.getRoles().add(role);
        log.info("Adding role to userDetails entity {}", userDetails);
        return credentialsRepository.save(userDetails);
    }

    public Credentials getCredentials(String username) {
        log.info("Get credentials by username {}", username);
        return credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found in the database"));
    }


}
