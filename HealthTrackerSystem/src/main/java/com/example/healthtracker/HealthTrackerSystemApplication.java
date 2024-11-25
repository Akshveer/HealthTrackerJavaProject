package com.example.healthtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HealthTrackerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthTrackerSystemApplication.class, args);
        System.out.println("Health Tracker System is up and running!");
    }
}
