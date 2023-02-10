package com.frederikzwartbol.springboottwitterrebuild.features.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByTwitterHandle(String twitterHandle);

    boolean existsByCredentials_username(String email);

    Optional<User> findUserByCredentials_username(String email);

    Page<User> findUserByTwitterHandleContaining(String twitterHandle, Pageable pageable);

    @Query(value = "SELECT u1 FROM User u1 WHERE :userId MEMBER OF u1.followers")
    Page<User> findFollowersOfUser(User userId, Pageable pageable);
}
