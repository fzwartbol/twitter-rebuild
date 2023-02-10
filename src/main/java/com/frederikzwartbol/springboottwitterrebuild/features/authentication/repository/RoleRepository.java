package com.frederikzwartbol.springboottwitterrebuild.features.authentication.repository;

import com.frederikzwartbol.springboottwitterrebuild.features.authentication.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(String role);
}
