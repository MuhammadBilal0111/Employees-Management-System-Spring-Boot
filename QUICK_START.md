# Quick Start Guide - Spring Boot

## 5-Minute Setup

### Step 1: Configure Database
Edit `src/main/resources/application.properties`:

**MySQL:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_management_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

**PostgreSQL:**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/employee_management_db
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

### Step 2: Build Project
```bash
mvn clean install
```

### Step 3: Run Application
```bash
mvn spring-boot:run
```

### Step 4: Test API
Open: `http://localhost:8080/api/swagger-ui.html`

---

## Key Features at a Glance

| Feature | Implementation |
|---------|----------------|
| **Database Relations** | 6 entities with proper FK constraints |
| **REST API** | 25+ endpoints with CRUD operations |
| **API Documentation** | Auto-generated Swagger/OpenAPI |
| **Validation** | Input validation with @Valid |
| **Service Layer** | Business logic separation |
| **Repositories** | Spring Data JPA abstraction |
| **Object Mapping** | ModelMapper for DTO transformations |
| **Transaction Mgmt** | @Transactional support |

---

## Common Tasks

### Add New Entity

1. **Create JPA Entity**
```java
@Entity
@Table(name = "skills")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
```

2. **Create DTOs**
```java
public class CreateSkillDto {
    @NotBlank
    private String name;
    
    @NotNull
    private Integer employeeId;
}
```

3. **Create Repository**
```java
@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
    List<Skill> findByEmployeeId(Integer employeeId);
}
```

4. **Create Service**
```java
@Service
@RequiredArgsConstructor
@Transactional
public class SkillService {
    private final SkillRepository skillRepository;
    
    public SkillDto createSkill(CreateSkillDto dto) {
        // Implementation
    }
}
```

5. **Create Controller**
```java
@RestController
@RequestMapping("/api/v1/skills")
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;
    
    @PostMapping
    public ResponseEntity<SkillDto> createSkill(@Valid @RequestBody CreateSkillDto dto) {
        return ResponseEntity.status(201).body(skillService.createSkill(dto));
    }
}
```

### Add Custom Query

```java
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT e FROM Employee e WHERE e.salary > :salary AND e.department.id = :deptId")
    List<Employee> findHighSalaryEmployees(@Param("salary") BigDecimal salary, 
                                           @Param("deptId") Integer deptId);
}
```

### Implement Search Filter

```java
@Service
public class EmployeeSearchService {
    private final EmployeeRepository repository;
    
    public List<Employee> search(String name, Integer deptId, BigDecimal minSalary) {
        Specification<Employee> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (name != null) {
                predicates.add(cb.like(root.get("firstName"), "%" + name + "%"));
            }
            if (deptId != null) {
                predicates.add(cb.equal(root.get("department").get("id"), deptId));
            }
            if (minSalary != null) {
                predicates.add(cb.ge(root.get("salary"), minSalary));
            }
            
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        
        return repository.findAll(spec);
    }
}
```

---

## API Testing

### Using Swagger UI (Easiest)
1. Run application
2. Navigate to `http://localhost:8080/api/swagger-ui.html`
3. Try each endpoint directly from UI
4. View request/response payloads

### Using cURL
```bash
# Create
curl -X POST http://localhost:8080/api/v1/employees \
  -H "Content-Type: application/json" \
  -d '{"firstName":"John","lastName":"Doe","email":"john@test.com","phoneNumber":"123","salary":50000,"hireDate":"2024-01-01T00:00:00","position":"Dev","departmentId":1}'

# Get all
curl http://localhost:8080/api/v1/employees

# Get one
curl http://localhost:8080/api/v1/employees/1

# Update
curl -X PUT http://localhost:8080/api/v1/employees/1 \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Jane","lastName":"Doe","email":"jane@test.com","phoneNumber":"456","salary":60000,"position":"Senior Dev","departmentId":1,"isActive":true}'

# Delete
curl -X DELETE http://localhost:8080/api/v1/employees/1
```

### Using Postman
1. Import Swagger URL: `http://localhost:8080/api/v3/api-docs`
2. Create requests for each endpoint
3. Save as collection
4. Share or version control

---

## Project Structure

```
src/main/java/com/employeemanagement/
├── models/                           # JPA Entities
│   └── Entities.java
├── repositories/                     # Spring Data Repositories
│   └── Repositories.java
├── services/                         # Business Logic
│   └── EmployeeService.java
├── controllers/                      # REST Controllers
│   └── EmployeeController.java
├── dto/                              # Data Transfer Objects
│   └── DTOs.java
├── config/                           # Spring Configuration
│   └── ApplicationConfig.java
└── EmployeeManagementApplication.java
```

---

## For Resume

**One-liner:**
> "Designed and built a full-stack Employee Management System in Spring Boot with MySQL, demonstrating expertise in enterprise application development, REST API design, and database design."

**Key accomplishments:**
1. ✅ Designed normalized MySQL database with 6 entities
2. ✅ Built REST API with 25+ endpoints using Spring Boot
3. ✅ Implemented Service layer pattern for separation of concerns
4. ✅ Created Swagger/OpenAPI documentation
5. ✅ Applied validation using Jakarta Bean Validation
6. ✅ Used Spring Data JPA for elegant ORM
7. ✅ Implemented dependency injection throughout
8. ✅ Configured transaction management with @Transactional

---

## Troubleshooting

### Connection Error
- Verify MySQL/PostgreSQL is running
- Check username and password
- Verify database name matches

### Port Already in Use
```bash
# Change port in application.properties
server.port=8081
```

### Maven Dependencies Error
```bash
# Clear Maven cache and reinstall
mvn clean install -U
```

### Swagger UI Not Loading
- Verify springdoc-openapi dependency is included
- Check `spring.mvc.servlet.path` in application.properties
- Verify application started successfully

---

## Performance Tips

1. **Add Indexes**
   ```sql
   CREATE INDEX idx_employee_email ON employees(email);
   CREATE INDEX idx_employee_dept ON employees(department_id);
   ```

2. **Use Lazy Loading**
   ```java
   @ManyToOne(fetch = FetchType.LAZY)
   private Department department;
   ```

3. **Implement Pagination**
   ```java
   Page<Employee> page = repository.findAll(PageRequest.of(0, 20));
   ```

4. **Use @Query with JOIN FETCH** (Prevent N+1)
   ```java
   @Query("SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.department")
   List<Employee> findAllWithDepartments();
   ```

5. **Enable Connection Pooling**
   ```properties
   spring.datasource.hikari.maximum-pool-size=10
   ```

---

## Interview Talking Points

### Architecture
- "Separated concerns using Service layer pattern"
- "Used Repository pattern for data access abstraction"
- "Implemented DTO pattern for API contracts"

### Database
- "Designed normalized schema with proper relationships"
- "Implemented foreign keys with cascading deletes"
- "Added indexes on frequently queried columns"

### Code Quality
- "Used Spring Data JPA for elegant ORM"
- "Implemented input validation with Jakarta Bean Validation"
- "Applied ModelMapper for clean DTO transformations"
- "Configured transaction management with @Transactional"

### API Design
- "RESTful endpoints following Spring conventions"
- "Proper HTTP status codes and methods"
- "Swagger documentation for all endpoints"

---

**Ready to impress in interviews! 💼**
