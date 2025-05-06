package com.example.ceybank.auth.repository;

import com.example.ceybank.auth.models.Role;
import com.example.ceybank.auth.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
