package com.example.healthtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Corresponds to login.html template
    }

    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup"; // Corresponds to signup.html template
    }
}
