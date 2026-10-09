# Employee Management System - REST API Documentation (Spring Boot)

## Base URL
```
http://localhost:8080/api/v1
```

## Swagger UI
```
http://localhost:8080/api/v3/api-docs
http://localhost:8080/api/swagger-ui.html
```

---

## EMPLOYEE ENDPOINTS

### GET /employees
Get all employees
```
Method: GET
URL: /api/v1/employees
Authentication: None
Response: 200 OK

[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "phoneNumber": "1234567890",
    "salary": 50000.00,
    "hireDate": "2023-01-15T00:00:00",
    "position": "Senior Developer",
    "isActive": true,
    "departmentId": 1,
    "departmentName": "IT",
    "createdAt": "2024-01-10T10:30:00"
  }
]
```

### GET /employees/{id}
Get employee by ID
```
Method: GET
URL: /api/v1/employees/1
Response: 200 OK / 404 Not Found
```

### POST /employees
Create new employee
```
Method: POST
URL: /api/v1/employees
Content-Type: application/json

Request:
{
  "firstName": "Jane",
  "lastName": "Smith",
  "email": "jane@example.com",
  "phoneNumber": "9876543210",
  "salary": 55000.00,
  "hireDate": "2024-01-15",
  "position": "Developer",
  "departmentId": 1
}

Response: 201 Created
{
  "id": 2,
  "firstName": "Jane",
  "lastName": "Smith",
  ...
}
```

### PUT /employees/{id}
Update employee
```
Method: PUT
URL: /api/v1/employees/1
Content-Type: application/json

Request:
{
  "firstName": "Jane",
  "lastName": "Smith",
  "email": "jane.smith@example.com",
  "phoneNumber": "9876543210",
  "salary": 60000.00,
  "position": "Senior Developer",
  "departmentId": 1,
  "isActive": true
}

Response: 200 OK
```

### DELETE /employees/{id}
Delete employee
```
Method: DELETE
URL: /api/v1/employees/1
Response: 204 No Content / 404 Not Found
```

### GET /employees/department/{departmentId}
Get employees by department
```
Method: GET
URL: /api/v1/employees/department/1
Response: 200 OK - List of employees
```

---

## DEPARTMENT ENDPOINTS

### GET /departments
Get all departments

### POST /departments
Create department
```json
{
  "name": "IT",
  "description": "Information Technology"
}
```

### PUT /departments/{id}
Update department

### DELETE /departments/{id}
Delete department

---

## PROJECT ENDPOINTS

### GET /projects
Get all projects

### GET /projects/{id}
Get project with assigned employees

### POST /projects
Create project
```json
{
  "name": "Project Alpha",
  "description": "Main product",
  "startDate": "2024-01-01T00:00:00",
  "endDate": "2024-12-31T23:59:59",
  "departmentId": 1
}
```

### PUT /projects/{id}
Update project

### DELETE /projects/{id}
Delete project

---

## ATTENDANCE ENDPOINTS

### POST /attendances/checkin
Employee check-in
```json
{
  "employeeId": 1
}

Response: 201 Created
{
  "id": 1,
  "employeeId": 1,
  "employeeName": "John Doe",
  "checkIn": "2024-01-15T09:00:00",
  "checkOut": null,
  "status": "Present"
}
```

### POST /attendances/checkout
Employee check-out

### GET /attendances/employee/{employeeId}
Get employee attendance history

---

## LEAVE ENDPOINTS

### POST /leaves
Request leave
```json
{
  "employeeId": 1,
  "fromDate": "2024-02-01T00:00:00",
  "toDate": "2024-02-05T23:59:59",
  "reason": "Vacation"
}

Response: 201 Created
```

### PUT /leaves/{id}/approve
Approve leave request

### PUT /leaves/{id}/reject
Reject leave request

### GET /leaves/employee/{employeeId}
Get employee leave history

---

## Error Handling

### Error Response Format
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "First name is required",
  "path": "/api/v1/employees"
}
```

### Common Status Codes
- `200 OK` - Success
- `201 Created` - Resource created
- `204 No Content` - Success (no response body)
- `400 Bad Request` - Validation error
- `404 Not Found` - Resource not found
- `409 Conflict` - Business logic conflict
- `500 Internal Server Error` - Server error

---

## Validation Rules

### Employee
- firstName: Required, Max 50 chars
- lastName: Required, Max 50 chars
- email: Required, Valid email format, Unique
- phoneNumber: Required, Max 20 chars
- salary: Required, Must be > 0
- hireDate: Required
- position: Required, Max 50 chars
- departmentId: Required, Must exist

### Project
- name: Required, Max 100 chars
- startDate: Required
- endDate: Must be >= startDate (if provided)
- departmentId: Required, Must exist

### Leave
- employeeId: Required, Must exist
- fromDate: Required
- toDate: Required, >= fromDate
- reason: Required, Max 500 chars

---

## Running the Application

### Prerequisites
- Java 17+
- MySQL or PostgreSQL
- Maven

### Build and Run
```bash
# Build
mvn clean package

# Run
mvn spring-boot:run

# Access Swagger UI
http://localhost:8080/api/swagger-ui.html
```

### Docker
```bash
docker build -t employee-management-system .
docker run -p 8080:8080 employee-management-system
```
