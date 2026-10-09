package com.employeemanagement.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "departments")
class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String name;
    
    private String description;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;
    
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "employees", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    
    @Column(nullable = false)
    private BigDecimal salary;
    
    @Column(name = "hire_date", nullable = false)
    private LocalDateTime hireDate;
    
    @Column(nullable = false)
    private String position;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeProject> employeeProjects;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendance> attendances;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Leave> leaves;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "projects")
class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String name;
    
    private String description;
    
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;
    
    @Column(name = "end_date")
    private LocalDateTime endDate;
    
    @Column(nullable = false)
    private String status = "Active";
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeProject> employeeProjects;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "employee_projects", 
    uniqueConstraints = {@UniqueConstraint(columnNames = {"employee_id", "project_id"})})
class EmployeeProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    
    @Column(nullable = false)
    private String role = "Member";
    
    @Column(name = "assigned_date", nullable = false)
    private LocalDateTime assignedDate = LocalDateTime.now();
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "attendances")
class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    
    @Column(name = "check_in", nullable = false)
    private LocalDateTime checkIn;
    
    @Column(name = "check_out")
    private LocalDateTime checkOut;
    
    @Column(nullable = false)
    private String status = "Present";
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "leaves")
class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    
    @Column(name = "from_date", nullable = false)
    private LocalDateTime fromDate;
    
    @Column(name = "to_date", nullable = false)
    private LocalDateTime toDate;
    
    @Column(nullable = false)
    private String reason;
    
    @Column(nullable = false)
    private String status = "Pending";
    
    @Column(name = "applied_date", nullable = false)
    private LocalDateTime appliedDate = LocalDateTime.now();
}
