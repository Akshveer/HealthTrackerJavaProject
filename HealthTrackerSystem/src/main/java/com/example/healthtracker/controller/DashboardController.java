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

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private HealthMetricService healthMetricService;

    @Autowired
    private FitnessGoalService fitnessGoalService;

    // GET method to show the dashboard
    @GetMapping
    public String showDashboard(@RequestParam("userId") Long userId, Model model) {
        // Fetch the user
        User user = userService.getUserById(userId);

        // Fetch the health metrics of the user
        List<HealthMetric> healthMetrics = healthMetricService.getHealthMetricsByUserId(userId);

        // Fetch the user's fitness goal
        FitnessGoal fitnessGoal = fitnessGoalService.getFitnessGoalById(userId).orElse(null);

        // Add the user, health metrics, and fitness goal to the model
        model.addAttribute("user", user);
        model.addAttribute("healthMetrics", healthMetrics);
        model.addAttribute("fitnessGoal", fitnessGoal);

        return "dashboard";  // Corresponds to dashboard.html template
    }

    // GET method to show the form for setting a fitness goal
    @GetMapping("/set-goal")
    public String showSetGoalForm(@RequestParam("userId") Long userId, Model model) {
        // Fetch the user
        User user = userService.getUserById(userId);

        // Add the user to the model
        model.addAttribute("user", user);

        return "set-goal";  // Corresponds to set-goal.html template
    }

    // POST method to set or update the fitness goal
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
}
