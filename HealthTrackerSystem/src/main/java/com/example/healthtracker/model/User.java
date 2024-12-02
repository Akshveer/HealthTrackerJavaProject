package com.example.healthtracker.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users") // Changing the table name because there was some error
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    private String name; // User's name
    private String email; // User's email
    private String password; // User's password

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Allow serialization of HealthMetrics from User
    private List<HealthMetric> healthMetrics; // List of health metrics associated with this user

    // Constructors
    public User() {}

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<HealthMetric> getHealthMetrics() {
        return healthMetrics;
    }

    public void setHealthMetrics(List<HealthMetric> healthMetrics) {
        this.healthMetrics = healthMetrics;
    }

    // Optional: Override toString for easier debugging
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", healthMetrics=" + healthMetrics +
                '}';
    }
}
