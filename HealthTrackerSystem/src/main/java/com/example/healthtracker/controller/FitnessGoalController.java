package com.example.healthtracker.controller;

import com.example.healthtracker.model.FitnessGoal;
import com.example.healthtracker.model.User; // Correctly imported at the top
import com.example.healthtracker.service.FitnessGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fitness-goal")
public class FitnessGoalController {

    @Autowired
    private FitnessGoalService fitnessGoalService;

    // Render the Set Goal Page
    @GetMapping
    public String showSetGoalPage() {
        return "set-goal"; // Renders the "set-goal.html" Thymeleaf template
    }

    // Create or update a fitness goal
    @PostMapping("/create")
    public String createFitnessGoal(@ModelAttribute FitnessGoal fitnessGoal, @RequestParam Long userId) {
        // Create a User object from the userId
        User user = new User();
        user.setId(userId); // Set the ID (assuming User has an `id` field)
        fitnessGoal.setUser(user); // Associate the user with the fitness goal

        // Save the fitness goal
        fitnessGoalService.saveFitnessGoal(fitnessGoal);

        // Redirect to the same page or another success page
        return "redirect:/fitness-goal";
    }
}
