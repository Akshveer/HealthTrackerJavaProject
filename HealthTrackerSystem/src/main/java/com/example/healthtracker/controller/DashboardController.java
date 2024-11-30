package com.example.healthtracker.controller;

import com.example.healthtracker.model.HealthMetric;
import com.example.healthtracker.model.User;
import com.example.healthtracker.service.UserService;
import com.example.healthtracker.service.HealthMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private HealthMetricService healthMetricService;

    // GET method to show the dashboard
    @GetMapping
    public String showDashboard(@RequestParam("userId") Long userId, Model model) {
        // Fetch the user
        User user = userService.getUserById(userId);

        // Fetch the health metrics of the user
        List<HealthMetric> healthMetrics = healthMetricService.getHealthMetricsByUserId(userId);

        // Add the user and health metrics to the model
        model.addAttribute("user", user);
        model.addAttribute("healthMetrics", healthMetrics);

        return "dashboard";  // This should correspond to a dashboard.html template
    }

    // Additional methods for handling features like setting fitness goals can go here
}
