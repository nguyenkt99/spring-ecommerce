package com.example.springecommerce.repository;

import com.example.springecommerce.entity.Role;
import com.example.springecommerce.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
