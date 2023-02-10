package com.frederikzwartbol.springboottwitterrebuild.features.authentication.repository;

import com.frederikzwartbol.springboottwitterrebuild.features.authentication.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
    Optional<Credentials> findByUsername(String username);
}
