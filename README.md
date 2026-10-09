# Employee Management System - Spring Boot

A production-ready, enterprise-grade Employee Management System built with **Spring Boot 3**, **Spring Data JPA**, and **MySQL**. Complete REST API with Swagger documentation, comprehensive database design, and interview preparation materials.

## 🎯 Features

- ✅ **Employee Management** - Complete CRUD operations
- ✅ **Department Management** - Organize employees
- ✅ **Project Management** - Assign employees to projects
- ✅ **Attendance Tracking** - Check-in/check-out functionality
- ✅ **Leave Management** - Leave requests and approvals
- ✅ **REST API** - 25+ endpoints with proper conventions
- ✅ **Swagger/OpenAPI** - Auto-generated API documentation
- ✅ **Spring Data JPA** - Elegant ORM abstraction
- ✅ **MySQL/PostgreSQL** - Production-ready database
- ✅ **Validation** - Input validation with @Valid
- ✅ **Async Operations** - Non-blocking I/O
- ✅ **Transaction Management** - @Transactional support

## 🏗️ Architecture

```
employee-management-system/
├── src/main/java/com/employeemanagement/
│   ├── models/              # JPA Entities
│   ├── repositories/        # Spring Data JPA Repositories
│   ├── services/            # Business Logic Layer
│   ├── controllers/         # REST Controllers
│   ├── dto/                 # Data Transfer Objects
│   ├── config/              # Spring Configuration
│   ├── EmployeeManagementApplication.java
│   └── resources/
│       └── application.properties
├── database/
│   └── DATABASE_SCHEMA.md   # SQL Schema & Diagram
├── docs/
│   └── API_DOCUMENTATION.md # REST API Reference
├── interviews/
│   └── INTERVIEW_QUESTIONS.md # 90+ Interview Questions
├── pom.xml                  # Maven Configuration
└── README.md
```

## 🗄️ Database Design

### Entities (6 Tables)
- **Department** - Company departments
- **Employee** - Employee records with salary, position
- **Project** - Projects assigned to departments
- **EmployeeProject** - Many-to-many relationship (junction table)
- **Attendance** - Check-in/check-out records
- **Leave** - Leave requests

### Key Features
- Foreign key constraints with cascading deletes
- Unique constraints (Email, Employee-Project combination)
- Indexes for query optimization
- Referential integrity
- Audit timestamps (created_at)

See `database/DATABASE_SCHEMA.md` for complete ER diagram and SQL scripts.

## 🚀 Getting Started

### Prerequisites
- **Java 17+** (JDK 17 or later)
- **Maven 3.8+**
- **MySQL 8.0+** or **PostgreSQL 12+**
- **IDE**: IntelliJ IDEA, Visual Studio Code, or Eclipse

### Installation

1. **Clone or extract the project**
   ```bash
   cd EmployeeManagementSystem_SpringBoot
   ```

2. **Update Database Configuration**
   
   Edit `src/main/resources/application.properties`:
   
   **For MySQL:**
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/employee_management_db
   spring.datasource.username=root
   spring.datasource.password=your_password
   spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
   ```
   
   **For PostgreSQL:**
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/employee_management_db
   spring.datasource.username=postgres
   spring.datasource.password=your_password
   spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
   ```

3. **Install Dependencies**
   ```bash
   mvn clean install
   ```

4. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the Application**
   ```
   Application: http://localhost:8080/api
   Swagger UI: http://localhost:8080/api/swagger-ui.html
   API Docs:   http://localhost:8080/api/v3/api-docs
   ```

## 📚 API Documentation

Complete API documentation is available in `docs/API_DOCUMENTATION.md` or visit Swagger UI at app startup.

### Quick API Examples

**Create Employee**
```bash
curl -X POST http://localhost:8080/api/v1/employees \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "phoneNumber": "1234567890",
    "salary": 50000.00,
    "hireDate": "2024-01-15T00:00:00",
    "position": "Developer",
    "departmentId": 1
  }'
```

**Get All Employees**
```bash
curl http://localhost:8080/api/v1/employees
```

**Get Employee by ID**
```bash
curl http://localhost:8080/api/v1/employees/1
```

**Update Employee**
```bash
curl -X PUT http://localhost:8080/api/v1/employees/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Jane",
    "lastName": "Doe",
    "salary": 55000.00,
    ...
  }'
```

**Delete Employee**
```bash
curl -X DELETE http://localhost:8080/api/v1/employees/1
```

## 🏗️ Project Structure

### Models (`com.employeemanagement.models`)
- **Department** - Department information
- **Employee** - Employee details with relationships
- **Project** - Company projects
- **EmployeeProject** - Junction table for many-to-many
- **Attendance** - Daily attendance records
- **Leave** - Leave requests

### DTOs (`com.employeemanagement.dto`)
- **CreateEmployeeDto** - Request for creating employees
- **UpdateEmployeeDto** - Request for updating employees
- **EmployeeDto** - Response object for API
- (Similar for other entities)

### Repositories (`com.employeemanagement.repositories`)
- **EmployeeRepository** - Spring Data JPA repository
- **DepartmentRepository**
- **ProjectRepository**
- **EmployeeProjectRepository**
- **AttendanceRepository**
- **LeaveRepository**

### Services (`com.employeemanagement.services`)
- **IEmployeeService** - Service interface
- **EmployeeService** - Business logic implementation
- Transaction management with @Transactional

### Controllers (`com.employeemanagement.controllers`)
- **EmployeeController** - REST endpoints
- Request validation with @Valid
- Swagger annotations for documentation

### Configuration (`com.employeemanagement.config`)
- **ApplicationConfig** - Spring beans configuration
- ModelMapper setup for DTO mapping
- Swagger/OpenAPI configuration

## 🔧 Technologies Used

| Technology | Version | Purpose |
|-----------|---------|---------|
| Spring Boot | 3.2.0 | Web Framework |
| Spring Data JPA | 3.2.0 | ORM & Database |
| MySQL Connector | 8.0.33 | MySQL Driver |
| PostgreSQL | 42.7.0 | PostgreSQL Driver |
| SpringDoc OpenAPI | 2.0.2 | Swagger Documentation |
| ModelMapper | 3.2.0 | Object Mapping |
| Lombok | Latest | Reduce Boilerplate |
| JUnit 5 | Latest | Testing Framework |
| Java | 17+ | Language |

## 📖 Key Concepts Demonstrated

### Dependency Injection
```java
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    // Automatically injected
}
```

### Repository Pattern
```java
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByDepartmentId(Integer departmentId);
}
```

### Service Layer
```java
@Service
@Transactional
public class EmployeeService implements IEmployeeService {
    public EmployeeDto createEmployee(CreateEmployeeDto dto) {
        // Business logic
    }
}
```

### REST Controller with Swagger
```java
@RestController
@RequestMapping("/api/v1/employees")
@Tag(name = "Employee Management")
public class EmployeeController {
    @GetMapping
    @Operation(summary = "Get all employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        // Implementation
    }
}
```

### JPA Relationships
```java
@Entity
public class Employee {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Attendance> attendances;
}
```

### Validation
```java
public class CreateEmployeeDto {
    @NotBlank(message = "First name is required")
    @Size(max = 50)
    private String firstName;
    
    @Email(message = "Must be valid email")
    private String email;
}
```

## 🔐 Security Considerations

This is a learning/portfolio project. For production:

1. **Add Authentication** - Spring Security with JWT
2. **Add Authorization** - Role-based access control (@PreAuthorize)
3. **Implement HTTPS** - SSL/TLS encryption
4. **Input Validation** - Already implemented via validation annotations
5. **SQL Injection Prevention** - Already handled by JPA parameterized queries
6. **CORS Configuration** - Use @CrossOrigin or WebMvcConfigurer
7. **Rate Limiting** - Implement via filters or third-party services
8. **Logging & Monitoring** - Use SLF4J with Logback or Slf4j-api

## 🧪 Testing

### Unit Tests Example
```java
@SpringBootTest
class EmployeeServiceTest {
    @MockBean
    private EmployeeRepository employeeRepository;
    
    @InjectMocks
    private EmployeeService employeeService;
    
    @Test
    void testCreateEmployee() {
        // Arrange, Act, Assert
    }
}
```

### REST Endpoint Test
```java
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void testGetAllEmployees() throws Exception {
        mockMvc.perform(get("/api/v1/employees"))
               .andExpect(status().isOk());
    }
}
```

## 📦 Building for Production

### Build Application
```bash
mvn clean package
```

### Run JAR
```bash
java -jar target/employee-management-system-1.0.0.jar
```

### Docker Build
```bash
docker build -t employee-management-system .
docker run -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/employee_management_db employee-management-system
```

## 📝 Database Migrations

With Hibernate auto-configuration, schema is created automatically. For manual migration:

```sql
-- Run the SQL scripts in database/DATABASE_SCHEMA.md
```

## 🎯 Interview Preparation

The `interviews/INTERVIEW_QUESTIONS.md` file contains 90+ interview questions covering:

- **Spring Boot** (15 questions)
- **Spring Data JPA** (15 questions)
- **Java OOP** (15 questions)
- **Design Patterns** (10 questions)
- **REST API Design** (10 questions)
- **Database Design** (10 questions)
- **Project-Specific** (5 questions)

## 💼 Resume Integration

**Add to your resume:**

```
Employee Management System - Portfolio Project (Spring Boot)
• Built REST API using Spring Boot 3 with 25+ endpoints
• Designed MySQL database with 6 entities and proper relationships
• Implemented Spring Data JPA with custom query methods
• Created Swagger/OpenAPI documentation for all endpoints
• Applied enterprise design patterns: Repository, Service, DTO
• Implemented validation using Jakarta Bean Validation
• Used ModelMapper for seamless object mapping
• Configured transaction management with @Transactional
```

---

**Happy Coding! 🚀**
