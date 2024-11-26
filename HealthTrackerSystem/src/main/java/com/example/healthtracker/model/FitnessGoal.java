package com.example.healthtracker.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class FitnessGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Relationship with User

    private String goalType; // Type of goal (e.g., "weight loss", "steps", "sleep")
    private String targetValue; // Target value for the goal
    private LocalDate startDate; // Start date of the goal
    private LocalDate endDate; // End date of the goal
    private boolean achieved; // Status of the goal (true if achieved, false otherwise)

    // Default Constructor (required by JPA)
    public FitnessGoal() {}

    // Parameterized Constructor
    public FitnessGoal(User user, String goalType, String targetValue, LocalDate startDate, LocalDate endDate, boolean achieved) {
        this.user = user;
        this.goalType = goalType;
        this.targetValue = targetValue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.achieved = achieved;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public String getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    // Optional: Override toString for easier debugging
    @Override
    public String toString() {
        return "FitnessGoal{" +
                "id=" + id +
                ", user=" + user +
                ", goalType='" + goalType + '\'' +
                ", targetValue='" + targetValue + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", achieved=" + achieved +
                '}';
    }
}
