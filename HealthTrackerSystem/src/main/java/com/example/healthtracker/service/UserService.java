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
    public User saveUser(User user) {
        return userRepository.save(user);
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

    // Method to authenticate the user (login)
    public User login(String email, String password) {
        User user = getUserByEmail(email);  // Reuse getUserByEmail method
        if (user != null && user.getPassword().equals(password)) {
            return user;  // If credentials match, return the user
        }
        return null;  // Return null if credentials don't match
    }
}
