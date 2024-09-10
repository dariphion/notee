package com.dariphion.notee.repositories;

import com.dariphion.notee.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Custom method to find a user by their username
    Optional<User> findByUsername(String username);

    // Optional method to find by email if needed
    Optional<User> findByEmail(String email);
}