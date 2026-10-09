package com.employeemanagement.services;

import com.employeemanagement.dto.CreateEmployeeDto;
import com.employeemanagement.dto.EmployeeDto;
import com.employeemanagement.dto.UpdateEmployeeDto;
import com.employeemanagement.models.Department;
import com.employeemanagement.models.Employee;
import com.employeemanagement.repositories.DepartmentRepository;
import com.employeemanagement.repositories.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

public interface IEmployeeService {
    List<EmployeeDto> getAllEmployees();
    EmployeeDto getEmployeeById(Integer id);
    EmployeeDto createEmployee(CreateEmployeeDto dto);
    EmployeeDto updateEmployee(Integer id, UpdateEmployeeDto dto);
    void deleteEmployee(Integer id);
    List<EmployeeDto> getEmployeesByDepartment(Integer departmentId);
}

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService implements IEmployeeService {
    
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> mapToDto(employee))
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public EmployeeDto getEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
        return mapToDto(employee);
    }
    
    @Override
    public EmployeeDto createEmployee(CreateEmployeeDto dto) {
        // Check if email already exists
        if (employeeRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists: " + dto.getEmail());
        }
        
        // Verify department exists
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + dto.getDepartmentId()));
        
        Employee employee = modelMapper.map(dto, Employee.class);
        employee.setDepartment(department);
        
        Employee savedEmployee = employeeRepository.save(employee);
        return mapToDto(savedEmployee);
    }
    
    @Override
    public EmployeeDto updateEmployee(Integer id, UpdateEmployeeDto dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
        
        // Check if new email is unique (if email changed)
        if (!employee.getEmail().equals(dto.getEmail()) && 
            employeeRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists: " + dto.getEmail());
        }
        
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + dto.getDepartmentId()));
        
        modelMapper.map(dto, employee);
        employee.setDepartment(department);
        
        Employee updatedEmployee = employeeRepository.save(employee);
        return mapToDto(updatedEmployee);
    }
    
    @Override
    public void deleteEmployee(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> getEmployeesByDepartment(Integer departmentId) {
        return employeeRepository.findByDepartmentId(departmentId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    
    private EmployeeDto mapToDto(Employee employee) {
        EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
        if (employee.getDepartment() != null) {
            dto.setDepartmentName(employee.getDepartment().getName());
        }
        return dto;
    }
}
