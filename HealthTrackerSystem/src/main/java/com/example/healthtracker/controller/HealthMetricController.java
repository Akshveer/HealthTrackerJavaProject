package com.example.healthtracker.controller;

import com.example.healthtracker.model.HealthMetric;
import com.example.healthtracker.model.User;
import com.example.healthtracker.repository.HealthMetricRepository;
import com.example.healthtracker.repository.UserRepository;
import com.example.healthtracker.service.HealthMetricService;
import com.example.healthtracker.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;  // Use @Controller for view rendering
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/metrics")
public class HealthMetricController {

    @Autowired
    private HealthMetricService healthMetricService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private HealthMetricRepository healthMetricRepository;

    @Autowired
    private UserRepository userRepository;

    // Get method to render the HTML page with a list of users
    @GetMapping
    public String showHealthMetricsPage(Model model) {
        // Pass a list of users to the page
        model.addAttribute("users", userService.getAllUsers());
        return "healthMetrics";  // Return the view name for Thymeleaf
    }

    // This method serves the API to get health metrics for a specific user
    @GetMapping("/{userId}")
    @ResponseBody  // Return the response as JSON (for AJAX)
    public List<HealthMetric> getMetricsByUserId(@PathVariable("userId") Long userId) {
        return healthMetricService.getMetricsByUserId(userId);
    }

    // This method handles saving a health metric
    
    @ResponseBody
    @PostMapping("/metrics/submit")
    public String submitHealthMetric(
            @RequestParam Long userId,
            @RequestParam double weight,
            @RequestParam int steps,
            @RequestParam double sleepHours,
            @RequestParam String date,
            Model model) {
        try {
            // Log the received data
            System.out.println("Received data: " + "userId=" + userId + ", weight=" + weight + ", steps=" + steps
                    + ", sleepHours=" + sleepHours + ", date=" + date);

            // Fetch the User based on userId
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Create and save the HealthMetric object
            HealthMetric healthMetric = new HealthMetric(user, weight, steps, sleepHours, date);
            healthMetricRepository.save(healthMetric);

            // Log the saved entity
            System.out.println("Saved HealthMetric: " + healthMetric);

            // Optionally add a success message to the model
            model.addAttribute("message", "Health metric submitted successfully!");

            // Redirect to success page or render success message
            return "redirect:/dashboard"; // Or return the success page name
        } catch (Exception e) {
            // Log the error
            System.out.println("Error: " + e.getMessage());
            model.addAttribute("error", "Failed to submit health metric. Please try again.");
            return "healthMetrics"; // Return to the form with error message
        }
    }
}