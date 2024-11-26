package com.example.healthtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    public String showWelcomePage() {
        return "welcome";  // Returns the view name, Thymeleaf will resolve to welcome.html
    }
}
