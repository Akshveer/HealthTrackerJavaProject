package com.example.healthtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.healthtracker.model.HealthMetric;
import com.example.healthtracker.service.HealthMetricService;

@RestController
@RequestMapping("/metrics")
public class HealthMetricController {
    @Autowired
    private HealthMetricService healthMetricService;

    @GetMapping("/{userId}")
    public List<HealthMetric> getMetricsByUserId(@PathVariable Long userId) {
        return healthMetricService.getMetricsByUserId(userId);
    }

    @PostMapping
    public HealthMetric saveMetric(@RequestBody HealthMetric metric) {
        return healthMetricService.saveMetric(metric);
    }

}