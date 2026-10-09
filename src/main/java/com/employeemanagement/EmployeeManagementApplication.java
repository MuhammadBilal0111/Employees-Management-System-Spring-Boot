package com.employeemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Employee Management System - Spring Boot Application
 * 
 * A comprehensive REST API for managing employees, departments, projects,
 * attendance, and leave requests using Spring Boot and JPA.
 * 
 * @author Muhammad Bilal
 * @version 1.0.0
 */
@SpringBootApplication
public class EmployeeManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }
}
