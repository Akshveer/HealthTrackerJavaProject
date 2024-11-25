package com.example.healthtracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class HealthMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId; // Maps to the user's ID (foreign key)

    private double weight; // In kilograms
    private int steps; // Number of steps taken
    private double sleepHours; // Hours of sleep
    private LocalDate date; // Date of the record

    // Default Constructor (required by JPA)
    public HealthMetric() {}

    // Parameterized Constructor
    public HealthMetric(Long userId, double weight, int steps, double sleepHours, LocalDate date) {
        this.userId = userId;
        this.weight = weight;
        this.steps = steps;
        this.sleepHours = sleepHours;
        this.date = date;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public double getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(double sleepHours) {
        this.sleepHours = sleepHours;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
