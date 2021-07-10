package com.example.springecommerce.repository;

import com.example.springecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
}
