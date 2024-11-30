package com.example.healthtracker.repository;

import com.example.healthtracker.model.FitnessGoal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FitnessGoalRepository extends JpaRepository<FitnessGoal, Long> {
	// Custom query to find fitness goal by userId
    List<FitnessGoal> findByUserId(Long userId);}
