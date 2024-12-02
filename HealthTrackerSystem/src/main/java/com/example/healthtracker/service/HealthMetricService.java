package com.example.healthtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.healthtracker.model.HealthMetric;
import com.example.healthtracker.repository.HealthMetricRepository;

@Service
public class HealthMetricService {

    @Autowired
    private HealthMetricRepository healthMetricRepository;

    // Fetch health metrics by user ID
    public List<HealthMetric> getMetricsByUserId(Long userId) {
        return healthMetricRepository.findByUserId(userId);
    }

    // Save a new health metric
    public HealthMetric saveMetric(HealthMetric metric) {
        return healthMetricRepository.save(metric);  // Save the metric and return the saved instance
    }
}
