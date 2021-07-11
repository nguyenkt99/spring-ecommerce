package com.example.springecommerce.repository;

import com.example.springecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    boolean existsByUsername(String username);
//    User findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
