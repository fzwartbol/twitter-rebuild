package com.frederikzwartbol.springbootjpamanytomany.authentication.repository;

import com.frederikzwartbol.springbootjpamanytomany.authentication.entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials,Long> {
    Optional<Credentials> findByUsername(String username);
}
