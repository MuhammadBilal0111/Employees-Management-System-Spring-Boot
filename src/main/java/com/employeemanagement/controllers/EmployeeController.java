package com.employeemanagement.controllers;

import com.employeemanagement.dto.CreateEmployeeDto;
import com.employeemanagement.dto.EmployeeDto;
import com.employeemanagement.dto.UpdateEmployeeDto;
import com.employeemanagement.services.IEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@Tag(name = "Employee Management", description = "APIs for managing employees")
public class EmployeeController {
    
    private final IEmployeeService employeeService;
    
    /**
     * Get all employees
     * @return List of all employees
     */
    @GetMapping
    @Operation(summary = "Get all employees", description = "Retrieve a list of all employees in the system")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved employees",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class)))
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    
    /**
     * Get employee by ID
     * @param id Employee ID
     * @return Employee details
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get employee by ID", description = "Retrieve employee details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee found"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer id) {
        EmployeeDto employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }
    
    /**
     * Create new employee
     * @param dto Employee creation data
     * @return Created employee
     */
    @PostMapping
    @Operation(summary = "Create new employee", description = "Create a new employee record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody CreateEmployeeDto dto) {
        EmployeeDto employee = employeeService.createEmployee(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }
    
    /**
     * Update employee
     * @param id Employee ID
     * @param dto Updated employee data
     * @return Updated employee
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update employee", description = "Update an existing employee record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateEmployeeDto dto) {
        EmployeeDto employee = employeeService.updateEmployee(id, dto);
        return ResponseEntity.ok(employee);
    }
    
    /**
     * Delete employee
     * @param id Employee ID
     * @return No content
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete employee", description = "Delete an employee record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Employee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Get employees by department
     * @param departmentId Department ID
     * @return List of employees in department
     */
    @GetMapping("/department/{departmentId}")
    @Operation(summary = "Get employees by department", description = "Retrieve all employees in a specific department")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved employees")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByDepartment(@PathVariable Integer departmentId) {
        List<EmployeeDto> employees = employeeService.getEmployeesByDepartment(departmentId);
        return ResponseEntity.ok(employees);
    }
}
