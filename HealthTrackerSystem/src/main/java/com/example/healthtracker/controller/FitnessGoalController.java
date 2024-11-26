package com.example.healthtracker.controller;

import com.example.healthtracker.model.FitnessGoal;
import com.example.healthtracker.service.FitnessGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fitness-goals")
public class FitnessGoalController {

    @Autowired
    private FitnessGoalService fitnessGoalService;

    // Create or update a fitness goal
    @PostMapping
    public ResponseEntity<FitnessGoal> createFitnessGoal(@RequestBody FitnessGoal fitnessGoal) {
        FitnessGoal savedGoal = fitnessGoalService.saveFitnessGoal(fitnessGoal);
        return ResponseEntity.ok(savedGoal);
    }

    // Get all fitness goals
    @GetMapping
    public List<FitnessGoal> getAllFitnessGoals() {
        return fitnessGoalService.getAllFitnessGoals();
    }

    // Get a fitness goal by ID
    @GetMapping("/{id}")
    public ResponseEntity<FitnessGoal> getFitnessGoalById(@PathVariable Long id) {
        Optional<FitnessGoal> fitnessGoal = fitnessGoalService.getFitnessGoalById(id);
        return fitnessGoal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a fitness goal by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFitnessGoal(@PathVariable Long id) {
        fitnessGoalService.deleteFitnessGoal(id);
        return ResponseEntity.noContent().build();
    }
}
