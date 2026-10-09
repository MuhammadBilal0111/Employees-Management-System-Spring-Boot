# Employee Management System - Spring Boot

I developed this backend using **Spring Boot 3**, **Spring Data JPA**, and **MySQL**. It is a complete REST API for managing employees, departments, projects, attendance, and leave, with Swagger documentation and a relational database design behind it.

## 🎯 What I Built

- **Employee Management** - Full CRUD operations
- **Department Management** - Organizing employees into departments
- **Project Management** - Assigning employees to projects
- **Attendance Tracking** - Check-in/check-out functionality
- **Leave Management** - Leave requests and approvals
- **REST API** - Endpoints that follow standard REST conventions
- **Swagger/OpenAPI** - Auto-generated API documentation
- **Validation** - Input validation with `@Valid`
- **Transaction Management** - `@Transactional` support in the service layer

## 🏗️ Architecture

I structured the backend in layers so each part has one responsibility:

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
├── pom.xml                  # Maven Configuration
└── README.md
```

## 🗄️ Database Design

I designed the database with six tables:

- **Department** - Company departments
- **Employee** - Employee records with salary and position
- **Project** - Projects assigned to departments
- **EmployeeProject** - Junction table for the many-to-many relationship
- **Attendance** - Check-in/check-out records
- **Leave** - Leave requests

Key design decisions:

- Foreign key constraints with cascading deletes
- Unique constraints (email, employee-project combination)
- Indexes for query optimization
- Referential integrity across all relationships
- Audit timestamps (`created_at`)

See `database/DATABASE_SCHEMA.md` for the complete ER diagram and SQL scripts.

## 🚀 Getting Started

### Prerequisites

- **Java 17+**
- **Maven 3.8+**
- **MySQL 8.0+** or **PostgreSQL 12+**
- **IDE**: IntelliJ IDEA, Visual Studio Code, or Eclipse

### Installation

1. **Clone or extract the project**
   ```bash
   cd EmployeeManagementSystem_SpringBoot
   ```

2. **Update the database configuration**

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

3. **Install dependencies**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the application**
   ```
   Application: http://localhost:8080/api
   Swagger UI:  http://localhost:8080/api/swagger-ui.html
   API Docs:    http://localhost:8080/api/v3/api-docs
   ```

## 🧱 Project Structure

### Models (`com.employeemanagement.models`)
Entities for Department, Employee, Project, EmployeeProject, Attendance, and Leave.

### DTOs (`com.employeemanagement.dto`)
- **CreateEmployeeDto** - Request for creating employees
- **UpdateEmployeeDto** - Request for updating employees
- **EmployeeDto** - Response object for the API
- Similar DTOs for the other entities

### Repositories (`com.employeemanagement.repositories`)
Spring Data JPA repositories: `EmployeeRepository`, `DepartmentRepository`, `ProjectRepository`, `EmployeeProjectRepository`, `AttendanceRepository`, `LeaveRepository`.

### Services (`com.employeemanagement.services`)
- **IEmployeeService** - Service interface
- **EmployeeService** - Business logic implementation with `@Transactional`

### Controllers (`com.employeemanagement.controllers`)
- **EmployeeController** - REST endpoints
- Request validation with `@Valid`
- Swagger annotations for documentation

### Configuration (`com.employeemanagement.config`)
- **ApplicationConfig** - Spring bean configuration
- ModelMapper setup for DTO mapping
- Swagger/OpenAPI configuration

## 🔧 Technologies Used

| Technology | Version | Purpose |
|-----------|---------|---------|
| Spring Boot | 3.2.0 | Web framework |
| Spring Data JPA | 3.2.0 | ORM & database access |
| MySQL Connector | 8.0.33 | MySQL driver |
| PostgreSQL | 42.7.0 | PostgreSQL driver |
| SpringDoc OpenAPI | 2.0.2 | Swagger documentation |
| ModelMapper | 3.2.0 | Object mapping |
| Lombok | Latest | Reduce boilerplate |
| JUnit 5 | Latest | Testing framework |
| Java | 17+ | Language |

## 📖 How I Implemented the Core Pieces

### Dependency Injection
```java
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    // Injected automatically through the constructor
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

The project currently focuses on the core backend. The next steps for a production deployment would be:

1. **Authentication** - Spring Security with JWT
2. **Authorization** - Role-based access control (`@PreAuthorize`)
3. **HTTPS** - SSL/TLS encryption
4. **Input validation** - Already implemented through validation annotations
5. **SQL injection prevention** - Already handled by JPA parameterized queries
6. **CORS configuration** - `@CrossOrigin` or `WebMvcConfigurer`
7. **Rate limiting** - Filters or a third-party service
8. **Logging & monitoring** - SLF4J with Logback

## 🧪 Testing

### Service Unit Test
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

### Build the application
```bash
mvn clean package
```

### Run the JAR
```bash
java -jar target/employee-management-system-1.0.0.jar
```

### Docker
```bash
docker build -t employee-management-system .
docker run -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/employee_management_db employee-management-system
```

## 📝 Database Migrations

With Hibernate auto-configuration, the schema is created automatically. For manual setup, run the SQL scripts in `database/DATABASE_SCHEMA.md`.

**Happy Coding! 🚀**