package com.example.healthtracker.repository;

import com.example.healthtracker.model.FitnessGoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FitnessGoalRepository extends JpaRepository<FitnessGoal, Long> {
    // You can add custom queries here if needed
}
