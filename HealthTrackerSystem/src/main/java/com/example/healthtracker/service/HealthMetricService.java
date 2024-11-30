package com.example.healthtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.healthtracker.model.HealthMetric;
import com.example.healthtracker.repository.HealthMetricRepository;

@Service
public class HealthMetricService {
	@Autowired
	// Using autowired means asking Spring to open the tool box and give a specific tool (bean) you need
	
	 private HealthMetricRepository healthMetricRepository;

    public List<HealthMetric> getMetricsByUserId(Long userId) {
        return healthMetricRepository.findByUserId(userId);
    }

    public HealthMetric saveMetric(HealthMetric metric) {
        return healthMetricRepository.save(metric);
    }
    
    public List<HealthMetric> getHealthMetricsByUserId(Long userId) {
        return healthMetricRepository.findByUserId(userId);  // Fetch health metrics by user ID
    }

	

}
