package com.example.healthtracker.service;

import com.example.healthtracker.model.FitnessGoal;
import com.example.healthtracker.repository.FitnessGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FitnessGoalService {

    @Autowired
    private FitnessGoalRepository fitnessGoalRepository;

    // Create or update a fitness goal
    public FitnessGoal saveFitnessGoal(FitnessGoal fitnessGoal) {
        return fitnessGoalRepository.save(fitnessGoal);
    }

    // Get all fitness goals
    public List<FitnessGoal> getAllFitnessGoals() {
        return fitnessGoalRepository.findAll();
    }

    // Get fitness goal by ID
    public Optional<FitnessGoal> getFitnessGoalById(Long id) {
        return fitnessGoalRepository.findById(id);
    }

    // Delete fitness goal by ID
    public void deleteFitnessGoal(Long id) {
        fitnessGoalRepository.deleteById(id);
    }
}
