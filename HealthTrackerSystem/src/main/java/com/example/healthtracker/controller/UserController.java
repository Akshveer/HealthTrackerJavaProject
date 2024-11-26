package com.example.healthtracker.controller;

import com.example.healthtracker.model.User;
import com.example.healthtracker.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create User (Signup)
    @PostMapping("/signup")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // Get all Users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get User by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Update User by ID
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // Delete User by ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Login (Authenticate User)
    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPassword());
    }
}
