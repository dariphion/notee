package com.dariphion.notee.services;

import com.dariphion.notee.domain.User;
import com.dariphion.notee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register a new user
    public User registerUser(User user) {
        // Check if the username already exists
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new IllegalStateException("Username is already taken");
        }

        // Add additional business logic, e.g., password hashing here (if needed)
        return userRepository.save(user);
    }

    // Find a user by username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Find a user by ID
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Delete a user by ID
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    // Additional methods for other user-related operations can be added here...
}