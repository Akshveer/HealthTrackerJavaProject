package com.example.healthtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Metrics;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.healthtracker.model.HealthMetric;
import com.example.healthtracker.service.HealthMetricService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/metrics")
public class HealthMetricController {
    @Autowired
    private HealthMetricService healthMetricService;

    // Fetch metrics for a specific user by ID
    @GetMapping("/{userId}")
    public List<HealthMetric> getMetricsByUserId(@PathVariable("userId") Long userId) {
        return healthMetricService.getMetricsByUserId(userId);
    }

    // Fetch metrics for the currently logged-in user
    @GetMapping
    public String showHealthMetricsPage() {
        return "healthMetrics"; // Do not include ".html"; Spring automatically resolves it.
    }

 // Redirect to the page where the user can add today's gains
    @PostMapping("/dashboard/{userId}")
    public String addGains(HttpServletRequest request) {
        // Your logic here, possibly storing today's gains
        return "redirect:/dashboard";  // Redirect to the /metrics endpoint after adding gains
    }


    
    // Save a new metric for a user
    @PostMapping
    public HealthMetric saveMetric(@RequestBody HealthMetric metric, HttpServletRequest request) {
        Long loggedInUserId = (Long) request.getSession().getAttribute("loggedInUserId");

        if (loggedInUserId == null) {
            throw new IllegalArgumentException("User is not logged in.");
        }

        // Set the logged-in user's ID as the owner of the metric
        metric.setId(loggedInUserId);

        return healthMetricService.saveMetric(metric);
    }
}
