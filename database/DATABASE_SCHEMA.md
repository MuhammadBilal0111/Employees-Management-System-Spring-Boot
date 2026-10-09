# Employee Management System - Database Schema (Spring Boot)

## Database Diagram

```
┌─────────────────────────┐
│     DEPARTMENTS         │
├─────────────────────────┤
│ PK: id (INT)            │
│ name (VARCHAR)          │
│ description (VARCHAR)   │
│ created_at (TIMESTAMP)  │
└──────────┬──────────────┘
           │ 1
           │ Has Many
           │
      ┌────┴─────────────────┐
      │                      │
      │ 0..*                 │ 0..*
      │ (FK: department_id)  │ (FK: department_id)
      │                      │
      ▼                      ▼
┌──────────────────────┐  ┌──────────────────────┐
│    EMPLOYEES         │  │     PROJECTS         │
├──────────────────────┤  ├──────────────────────┤
│ PK: id (INT)         │  │ PK: id (INT)         │
│ first_name (VARCHAR) │  │ name (VARCHAR)       │
│ last_name (VARCHAR)  │  │ description (VARCHAR)│
│ email (VARCHAR)*     │  │ start_date (DATETIME)│
│ phone_number (VARCHAR)  │ end_date (DATETIME)  │
│ salary (DECIMAL)     │  │ status (VARCHAR)     │
│ hire_date (DATETIME) │  │ created_at (TIMESTAMP)
│ position (VARCHAR)   │  └──────────┬───────────┘
│ is_active (BOOLEAN)  │             │ 1
│ department_id (FK)   │             │ Has Many
│ created_at (TIMESTAMP)  │          │
└──────────┬───────────┘  │     0..*
           │ 1            │ (FK: project_id)
           │ Has Many      │
           │               ▼
      0..*│         ┌──────────────────────────────┐
    (FK: ├────────→ │   EMPLOYEE_PROJECTS (Junction)
   employee_id)     ├──────────────────────────────┤
           │        │ PK: id (INT)                 │
      ┌────┴─────────├─ employee_id (FK)           │
      │ │            │ project_id (FK)             │
      │ │            │ role (VARCHAR)              │
      │ │            │ assigned_date (TIMESTAMP)   │
      │ │            │ UNIQUE(employee_id, project_id)
      │ │            └──────────────────────────────┘
      │ │                     ▲
      │ │                     │
      └─┼─────────────────────┘
        │
        │
        ├─ 0..* (FK: employee_id)
        │                      ▼
        │            ┌────────────────────┐
        │            │   ATTENDANCES      │
        │            ├────────────────────┤
        │            │ PK: id (INT)       │
        │            │ employee_id (FK)   │
        │            │ check_in (DATETIME)│
        │            │ check_out (DATETIME)
        │            │ status (VARCHAR)   │
        │            └────────────────────┘
        │
        │ 0..*
        │ (FK: employee_id)
        │
        ▼
    ┌────────────────────┐
    │     LEAVES         │
    ├────────────────────┤
    │ PK: id (INT)       │
    │ employee_id (FK)   │
    │ from_date (DATETIME)
    │ to_date (DATETIME) │
    │ reason (VARCHAR)   │
    │ status (VARCHAR)   │
    │ applied_date (TIMESTAMP)
    └────────────────────┘
```

## SQL Schema

```sql
-- Create Departments Table
CREATE TABLE departments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Employees Table
CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL,
    salary DECIMAL(18,2) NOT NULL,
    hire_date DATETIME NOT NULL,
    position VARCHAR(50) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    department_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE RESTRICT,
    INDEX idx_department_id (department_id),
    INDEX idx_email (email),
    INDEX idx_is_active (is_active)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Projects Table
CREATE TABLE projects (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    start_date DATETIME NOT NULL,
    end_date DATETIME,
    status VARCHAR(50) DEFAULT 'Active',
    department_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE RESTRICT,
    INDEX idx_department_id (department_id),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Employee_Projects Junction Table
CREATE TABLE employee_projects (
    id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    project_id INT NOT NULL,
    role VARCHAR(50) DEFAULT 'Member',
    assigned_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    UNIQUE KEY unique_emp_proj (employee_id, project_id),
    INDEX idx_employee_id (employee_id),
    INDEX idx_project_id (project_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Attendances Table
CREATE TABLE attendances (
    id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    check_in DATETIME NOT NULL,
    check_out DATETIME,
    status VARCHAR(50) DEFAULT 'Present',
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE,
    INDEX idx_employee_id (employee_id),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Leaves Table
CREATE TABLE leaves (
    id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    from_date DATETIME NOT NULL,
    to_date DATETIME NOT NULL,
    reason VARCHAR(500) NOT NULL,
    status VARCHAR(50) DEFAULT 'Pending',
    applied_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE,
    INDEX idx_employee_id (employee_id),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
```

## Sample Data Insertion

```sql
-- Insert Departments
INSERT INTO departments (name, description) VALUES
('IT', 'Information Technology'),
('HR', 'Human Resources'),
('Sales', 'Sales Department');

-- Insert Employees
INSERT INTO employees (first_name, last_name, email, phone_number, salary, hire_date, position, department_id) VALUES
('John', 'Doe', 'john@example.com', '1234567890', 50000, '2023-01-15', 'Senior Developer', 1),
('Jane', 'Smith', 'jane@example.com', '9876543210', 55000, '2023-02-20', 'Developer', 1),
('Mike', 'Johnson', 'mike@example.com', '5555555555', 60000, '2023-03-10', 'Project Manager', 3);

-- Insert Projects
INSERT INTO projects (name, description, start_date, status, department_id) VALUES
('Project Alpha', 'Main product development', '2024-01-01', 'Active', 1),
('Project Beta', 'New initiative', '2024-02-01', 'Active', 1);

-- Insert Employee-Projects
INSERT INTO employee_projects (employee_id, project_id, role) VALUES
(1, 1, 'Lead Developer'),
(2, 1, 'Developer'),
(3, 2, 'Manager');
```

## Key Features

- **Referential Integrity**: Foreign keys with CASCADE/RESTRICT delete options
- **Unique Constraints**: Email unique, Employee-Project combination unique
- **Indexes**: Created on Foreign Keys and frequently queried columns for optimization
- **Timestamps**: created_at fields for audit trail
- **Decimal Precision**: Salary with 18,2 precision for financial accuracy
- **Engine**: InnoDB for transaction support
- **Charset**: UTF-8 for internationalization
