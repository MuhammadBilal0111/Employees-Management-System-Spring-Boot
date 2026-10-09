package com.employeemanagement.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;

// ============ EMPLOYEE DTOs ============
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
class CreateEmployeeDto {
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be less than 50 characters")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be less than 50 characters")
    private String lastName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    
    @NotBlank(message = "Phone number is required")
    @Size(max = 20, message = "Phone number must be less than 20 characters")
    private String phoneNumber;
    
    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", exclusive = true, message = "Salary must be greater than 0")
    private BigDecimal salary;
    
    @NotNull(message = "Hire date is required")
    private LocalDateTime hireDate;
    
    @NotBlank(message = "Position is required")
    @Size(max = 50, message = "Position must be less than 50 characters")
    private String position;
    
    @NotNull(message = "Department ID is required")
    @Positive(message = "Department ID must be positive")
    private Integer departmentId;
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
class UpdateEmployeeDto {
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be less than 50 characters")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be less than 50 characters")
    private String lastName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    
    @NotBlank(message = "Phone number is required")
    @Size(max = 20, message = "Phone number must be less than 20 characters")
    private String phoneNumber;
    
    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", exclusive = true, message = "Salary must be greater than 0")
    private BigDecimal salary;
    
    @NotBlank(message = "Position is required")
    @Size(max = 50, message = "Position must be less than 50 characters")
    private String position;
    
    @NotNull(message = "Department ID is required")
    @Positive(message = "Department ID must be positive")
    private Integer departmentId;
    
    private Boolean isActive = true;
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
class EmployeeDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private BigDecimal salary;
    private LocalDateTime hireDate;
    private String position;
    private Boolean isActive;
    private Integer departmentId;
    private String departmentName;
    private LocalDateTime createdAt;
}

// ============ DEPARTMENT DTOs ============
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
class CreateDepartmentDto {
    @NotBlank(message = "Department name is required")
    @Size(max = 100, message = "Department name must be less than 100 characters")
    private String name;
    
    private String description;
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
class UpdateDepartmentDto {
    @NotBlank(message = "Department name is required")
    @Size(max = 100, message = "Department name must be less than 100 characters")
    private String name;
    
    private String description;
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
class DepartmentDto {
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Integer employeeCount;
}

// ============ PROJECT DTOs ============
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
class CreateProjectDto {
    @NotBlank(message = "Project name is required")
    @Size(max = 100, message = "Project name must be less than 100 characters")
    private String name;
    
    private String description;
    
    @NotNull(message = "Start date is required")
    private LocalDateTime startDate;
    
    private LocalDateTime endDate;
    
    @NotNull(message = "Department ID is required")
    @Positive(message = "Department ID must be positive")
    private Integer departmentId;
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
class UpdateProjectDto {
    @NotBlank(message = "Project name is required")
    private String name;
    
    private String description;
    private LocalDateTime endDate;
    private String status;
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
class ProjectDto {
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;
    private Integer departmentId;
    private String departmentName;
    private LocalDateTime createdAt;
    private List<EmployeeProjectDto> employees;
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
class EmployeeProjectDto {
    private Integer employeeId;
    private String employeeName;
    private String role;
    private LocalDateTime assignedDate;
}

// ============ ATTENDANCE DTOs ============
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
class CheckInDto {
    @NotNull(message = "Employee ID is required")
    @Positive(message = "Employee ID must be positive")
    private Integer employeeId;
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
class AttendanceDto {
    private Integer id;
    private Integer employeeId;
    private String employeeName;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String status;
}

// ============ LEAVE DTOs ============
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
class CreateLeaveDto {
    @NotNull(message = "Employee ID is required")
    @Positive(message = "Employee ID must be positive")
    private Integer employeeId;
    
    @NotNull(message = "From date is required")
    private LocalDateTime fromDate;
    
    @NotNull(message = "To date is required")
    private LocalDateTime toDate;
    
    @NotBlank(message = "Reason is required")
    @Size(max = 500, message = "Reason must be less than 500 characters")
    private String reason;
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
class LeaveDto {
    private Integer id;
    private Integer employeeId;
    private String employeeName;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private String reason;
    private String status;
    private LocalDateTime appliedDate;
}
