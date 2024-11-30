package com.example.healthtracker.service;

import com.example.healthtracker.model.FitnessGoal;
import com.example.healthtracker.model.User;
import com.example.healthtracker.repository.FitnessGoalRepository;
import com.example.healthtracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FitnessGoalService {

    @Autowired
    private FitnessGoalRepository fitnessGoalRepository;

    @Autowired
    private UserRepository userRepository;

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

    // Get fitness goal by User ID
    public List<FitnessGoal> getFitnessGoalsByUserId(Long userId) {
        return fitnessGoalRepository.findByUserId(userId);
    }

    // Set or update fitness goal for a user
    public FitnessGoal setFitnessGoal(Long userId, FitnessGoal newFitnessGoal) {
        // Find the user by ID
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + userId + " not found.");
        }

        User user = userOptional.get();

        // Set the user and save the fitness goal
        newFitnessGoal.setUser(user);
        return fitnessGoalRepository.save(newFitnessGoal);
    }
}
