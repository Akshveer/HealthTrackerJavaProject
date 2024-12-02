package com.example.healthtracker.controller;

import com.example.healthtracker.model.FitnessGoal;
import com.example.healthtracker.model.HealthMetric;
import com.example.healthtracker.model.User;
import com.example.healthtracker.service.FitnessGoalService;
import com.example.healthtracker.service.HealthMetricService;
import com.example.healthtracker.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private HealthMetricService healthMetricService;

    @Autowired
    private FitnessGoalService fitnessGoalService;

    /**
     * GET method to show the dashboard
     */
    @GetMapping
    public String showDashboard(@RequestParam("userId") Long userId, Model model) {
        // Fetch the user
        User user = userService.getUserById(userId);

        // Fetch the health metrics of the user
        List<HealthMetric> healthMetrics = healthMetricService.getHealthMetricsByUserId(userId);

        // For demo purposes, assuming these are values derived from your health metrics
        double caloriesBurned = 300; // Example data, replace with actual logic
        double waterIntake = 2.5;   // Example data
        double exerciseTime = 1.5;  // Example data

        // Steps data for the bar chart (replace with actual data logic)
        List<Integer> dailySteps = healthMetrics.stream()
                .map(HealthMetric::getSteps)
                .collect(Collectors.toList());

        // Add the data to the model for rendering in the view
        model.addAttribute("user", user);
        model.addAttribute("caloriesBurned", caloriesBurned);
        model.addAttribute("waterIntake", waterIntake);
        model.addAttribute("exerciseTime", exerciseTime);
        model.addAttribute("dailySteps", dailySteps);

        return "dashboard";  // Corresponds to dashboard.html template
    }

    /**
     * POST method to set or update the fitness goal
     */
    @PostMapping("/set-goal")
    public String setFitnessGoal(
            @RequestParam Long userId,
            @RequestParam String goalType,
            @RequestParam String targetValue,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        // Fetch the user
        User user = userService.getUserById(userId);

        // Fetch or create a new fitness goal for the user
        FitnessGoal fitnessGoal = fitnessGoalService.getFitnessGoalById(userId).orElse(null);
        if (fitnessGoal == null) {
            fitnessGoal = new FitnessGoal();
            fitnessGoal.setUser(user);
        }

        // Update fitness goal details
        fitnessGoal.setGoalType(goalType);
        fitnessGoal.setTargetValue(targetValue);
        fitnessGoal.setStartDate(LocalDate.parse(startDate));
        fitnessGoal.setEndDate(LocalDate.parse(endDate));

        // Save the updated fitness goal
        fitnessGoalService.saveFitnessGoal(fitnessGoal);

        return "redirect:/dashboard?userId=" + userId;  // Redirect back to the dashboard
    }

    /**
     * API endpoint to fetch health metrics for charts/graphs
     */
    @GetMapping("/data")
    @ResponseBody
    public List<HealthMetric> fetchHealthMetrics(@RequestParam("userId") Long userId) {
        // Return health metrics data as JSON for graph rendering
        return healthMetricService.getHealthMetricsByUserId(userId);
    }
}
