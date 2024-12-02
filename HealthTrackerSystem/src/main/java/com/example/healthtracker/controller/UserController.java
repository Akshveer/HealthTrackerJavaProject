package com.example.healthtracker.controller;

import com.example.healthtracker.model.User;
import com.example.healthtracker.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")  // Controller for managing user interactions
public class UserController {

    @Autowired
    private UserService userService;

    // GET method to show the signup form
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup"; // Render signup.html template
    }

    // POST method for signing up a user
    @PostMapping("/signup")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            RedirectAttributes redirectAttributes) {
        try {
            if (userService.getUserByEmail(email) != null) {
                redirectAttributes.addFlashAttribute("error", "Email is already in use.");
                return "redirect:/signup"; // Redirect back to signup page
            }

            User user = new User();
            user.setName(username);
            user.setEmail(email);
            user.setPassword(password);
            userService.saveUser(user);

            redirectAttributes.addFlashAttribute("success", "Signup successful! You can now log in.");
            return "redirect:/login"; // Redirect to login page
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred while signing up.");
            return "redirect:/signup";
        }
    }
    
    


    // GET method to show the login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Render login.html template
    }

    // POST method for user login
    @PostMapping("/login")
    public String loginUser(
            @RequestParam String email,
            @RequestParam String password,
            HttpServletRequest request, // Added HttpServletRequest to work with session
            Model model) {

        User existingUser = userService.getUserByEmail(email); // Finds the user by email

        if (existingUser == null) {
            model.addAttribute("error", "User not found");
            return "login"; // Stay on login page
        }

        if (!existingUser.getPassword().equals(password)) { // Check password
            model.addAttribute("error", "Invalid credentials");
            return "login"; // Stay on login page
        }

        // Store the user ID in the server-side session
        request.getSession().setAttribute("loggedInUserId", existingUser.getId());

        model.addAttribute("user", existingUser);
        return "redirect:/dashboard?userId=" + existingUser.getId(); // Redirect to dashboard with userId
    }
}