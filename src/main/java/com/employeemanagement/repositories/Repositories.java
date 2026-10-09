package com.employeemanagement.repositories;

import com.employeemanagement.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByName(String name);
}

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByEmail(String email);
    
    List<Employee> findByDepartmentId(Integer departmentId);
    
    List<Employee> findByIsActive(Boolean isActive);
}

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByDepartmentId(Integer departmentId);
    
    List<Project> findByStatus(String status);
}

@Repository
public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Integer> {
    List<EmployeeProject> findByEmployeeId(Integer employeeId);
    
    List<EmployeeProject> findByProjectId(Integer projectId);
    
    Optional<EmployeeProject> findByEmployeeIdAndProjectId(Integer employeeId, Integer projectId);
}

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    List<Attendance> findByEmployeeId(Integer employeeId);
    
    List<Attendance> findByStatus(String status);
}

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {
    List<Leave> findByEmployeeId(Integer employeeId);
    
    List<Leave> findByStatus(String status);
}
