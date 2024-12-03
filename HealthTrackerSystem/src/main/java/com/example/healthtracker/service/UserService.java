package com.example.healthtracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.healthtracker.model.User;
import com.example.healthtracker.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Method to register a new user
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // Method to fetch all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Method to get a user by email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Method to save or update a user
    public void saveUser(User user) {
        userRepository.save(user);
    }

    // Method to get a user by ID
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);  // Return null if user not found
    }

    // Method to update a user
    public User updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);  // Ensure the ID stays the same during the update
            return userRepository.save(user);
        } else {
            return null;  // Return null if the user doesn't exist
        }
    }

    // Method to delete a user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Authenticate user by email and password
    public User authenticate(String email, String password) {
        // Find the user by email
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            // Password matches, return user
            return user;
        }
        // If no user found or password doesn't match
        return null;
    }
    
    
}
