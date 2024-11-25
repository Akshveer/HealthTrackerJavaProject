package com.example.healthtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.healthtracker.model.HealthMetric;

public interface HealthMetricRepository extends JpaRepository <HealthMetric, Long> {
	 List<HealthMetric> findByUserId(Long userId);

}
