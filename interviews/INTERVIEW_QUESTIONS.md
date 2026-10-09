# Spring Boot Interview Questions - Employee Management System

## Comprehensive Interview Guide (85+ Questions)

---

## SPRING BOOT & SPRING FRAMEWORK

### Beginner Level

1. **What is Spring Boot and what problems does it solve?**
   - Answer: Spring Boot is an opinionated framework that simplifies Spring application development. It provides auto-configuration, embedded servers, and standalone jar packaging, eliminating boilerplate code and making development faster.

2. **What is the difference between Spring and Spring Boot?**
   - Answer: Spring requires manual configuration (XML/Java); Spring Boot provides auto-configuration, embedded Tomcat, starter dependencies, and production-ready features out of the box.

3. **What is auto-configuration in Spring Boot?**
   - Answer: Auto-configuration automatically configures Spring application based on jar dependencies on the classpath. Use @EnableAutoConfiguration or @SpringBootApplication to enable it.

4. **What are Spring Boot Starters?**
   - Answer: Pre-configured dependency descriptors that simplify Maven/Gradle configuration. Example: spring-boot-starter-web includes Spring MVC, Tomcat, and Jackson.

5. **What is @SpringBootApplication annotation?**
   - Answer: Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan. It marks the main entry point of a Spring Boot application.

6. **What is application.properties file used for?**
   - Answer: Externalize application configuration. Contains database URL, server port, logging levels, and custom properties without hardcoding.

### Intermediate Level

7. **How does Spring Boot auto-configuration work internally?**
   - Answer: Classpath scanning finds META-INF/spring.factories; conditional beans created based on @Conditional annotations; properties override defaults in application.properties.

8. **What is the difference between @Component, @Service, @Repository, and @Controller?**
   - Answer:
     - @Component: Generic bean
     - @Service: Business logic layer
     - @Repository: Data access layer; enables persistence exception translation
     - @Controller: Web layer; handles HTTP requests

9. **Explain dependency injection in Spring Boot.**
   - Answer: Spring automatically injects dependencies through constructor, setter, or field injection. Promotes loose coupling and testability.

10. **What are bean scopes in Spring?**
    - Answer: Singleton (one instance), Prototype (new instance each time), Request (per HTTP request), Session (per user session), Application (global).

11. **What is @Autowired and how does it work?**
    - Answer: Marks a field, constructor, or method for automatic dependency injection. Spring resolves beans by type; uses @Qualifier or @Primary for disambiguation.

12. **Explain the difference between constructor and setter injection.**
    - Answer: Constructor injection is mandatory, immutable, good for required dependencies; setter injection is optional, flexible, good for optional dependencies.

### Advanced Level

13. **What is Spring Bean Lifecycle?**
    - Answer: Instantiation → Populate properties → Set Bean Name Aware → Set Bean Factory Aware → Pre-initialization (BeanPostProcessor) → Initialize → Post-initialization → Ready → Destroy.

14. **How does Spring manage transactions?**
    - Answer: @Transactional annotation; declarative transaction management via AOP; supports ACID properties (Atomicity, Consistency, Isolation, Durability).

15. **What is @Transactional readOnly property and when to use it?**
    - Answer: Hint to database to optimize read-only queries; use for methods that only read data, improves performance by skipping write-related overhead.

16. **Explain aspect-oriented programming (AOP) in Spring.**
    - Answer: Cross-cutting concerns (logging, security, transactions) implemented separately; applied to business logic via proxies without modifying code.

17. **What is the difference between JpaRepository and CrudRepository?**
    - Answer: CrudRepository provides CRUD operations; JpaRepository extends it plus batch operations, flush, delete in batch, pagination/sorting.

18. **How does Spring MVC request processing work?**
    - Answer: DispatcherServlet → HandlerMapping → Controller → Service → Repository → Database → Response rendered by ViewResolver.

---

## SPRING DATA JPA

### Beginner Level

19. **What is JPA (Java Persistence API)?**
    - Answer: Standard Java API for object-relational mapping (ORM). Maps Java objects to database tables; provides CRUD operations and query language (JPQL).

20. **What is Hibernate?**
    - Answer: Most popular JPA implementation. Provides ORM functionality, lazy loading, caching, and query optimization.

21. **What is the @Entity annotation?**
    - Answer: Marks a class as JPA entity (mapped to database table). Required for persistent objects.

22. **What is @Table annotation?**
    - Answer: Specifies table name and constraints in database. Default table name is lowercase class name.

23. **What is @Id annotation?**
    - Answer: Marks primary key field. Must be present in every entity.

24. **What is @GeneratedValue?**
    - Answer: Specifies ID generation strategy: IDENTITY (auto-increment), SEQUENCE, TABLE, UUID.

### Intermediate Level

25. **What are different fetch types in JPA?**
    - Answer:
      - LAZY: Loads only when accessed; reduces memory usage
      - EAGER: Loads immediately; useful for frequently accessed data

26. **What is the difference between @OneToOne, @OneToMany, @ManyToOne, and @ManyToMany?**
    - Answer:
      - @OneToOne: Single object to single object
      - @OneToMany: One object to many objects
      - @ManyToOne: Many objects to one object
      - @ManyToMany: Many to many via junction table

27. **What is cascade in JPA?**
    - Answer: Cascade operations (persist, merge, remove, refresh) from parent to child entities. CascadeType.ALL cascades all operations.

28. **What is orphanRemoval in JPA?**
    - Answer: Automatically removes child entities when removed from parent collection; useful for one-to-many relationships.

29. **What is the N+1 query problem?**
    - Answer: Fetching one parent, then N queries for each child entity; solved by using JOIN FETCH or batch loading.

30. **How do you prevent N+1 queries in Spring Data JPA?**
    - Answer: Use @Query with JOIN FETCH, @EntityGraph, implement DTO projection, or use batch loading.

### Advanced Level

31. **What is JPQL (Java Persistence Query Language)?**
    - Answer: Object-oriented query language for querying JPA entities; similar to SQL but works with objects not tables.

32. **Explain the difference between JPQL and SQL.**
    - Answer: JPQL queries entity classes; SQL queries tables. JPQL is portable across databases; more abstract.

33. **What is the @Query annotation?**
    - Answer: Custom JPQL or native SQL queries in repository methods; allows complex queries without custom implementations.

34. **What is native query in JPA?**
    - Answer: Execute raw SQL directly on database; use nativeQuery=true in @Query; less portable but more flexible.

35. **What is Spring Data JPA Specification?**
    - Answer: Programmatic way to build dynamic queries; useful for complex filtering without writing JPQL strings.

---

## JAVA & OOP CONCEPTS

### Beginner Level

36. **What are the four pillars of OOP?**
    - Answer:
      - Encapsulation: Bundle data and methods
      - Abstraction: Hide internal details
      - Inheritance: Extend functionality
      - Polymorphism: Same interface, multiple forms

37. **What is the difference between abstract class and interface?**
    - Answer: Abstract class can have state and methods; interface is pure contract. Single inheritance with classes; multiple with interfaces.

38. **What is the purpose of access modifiers?**
    - Answer: public (everywhere), protected (same package + subclasses), default (same package), private (same class only).

39. **What is method overloading and overriding?**
    - Answer: Overloading: same name, different parameters; compile-time; Overriding: same signature, different implementation; runtime.

40. **What is the difference between String, StringBuilder, and StringBuffer?**
    - Answer: String is immutable; StringBuilder is mutable, not thread-safe; StringBuffer is mutable, thread-safe.

### Intermediate Level

41. **What is the difference between final, finally, and finalize?**
    - Answer: final: constant/prevent override; finally: block always executes; finalize: called before garbage collection.

42. **What is garbage collection in Java?**
    - Answer: Automatic memory management; unreferenced objects are collected automatically; improves memory efficiency.

43. **What are Java collections and their hierarchy?**
    - Answer: Collection → List (ArrayList, LinkedList), Set (HashSet, TreeSet), Queue (PriorityQueue); Map (HashMap, TreeMap).

44. **What is the difference between HashMap and Hashtable?**
    - Answer: HashMap is not thread-safe; Hashtable is thread-safe but slower; HashMap allows null key/values; Hashtable doesn't.

45. **What is exception handling in Java?**
    - Answer: Mechanism to handle runtime errors using try-catch-finally blocks; enables graceful error handling without crashing.

### Advanced Level

46. **What are checked and unchecked exceptions?**
    - Answer: Checked: must be caught or declared; compile-time check; Unchecked: runtime exceptions; don't require catch.

47. **What is the difference between throw and throws?**
    - Answer: throw: explicitly throw exception; throws: declare exception in method signature.

48. **What are streams in Java 8?**
    - Answer: Functional-style operations on collections; lazy evaluation; enable map, filter, reduce operations; parallel processing support.

49. **What are lambda expressions?**
    - Answer: Anonymous functions using -> syntax; enables functional programming; requires single abstract method (SAM).

50. **What is the difference between List.stream() and List.parallelStream()?**
    - Answer: stream() is sequential; parallelStream() uses multiple threads; parallel is faster for large datasets.

---

## DESIGN PATTERNS & ARCHITECTURE

### Beginner Level

51. **What is a design pattern?**
    - Answer: Reusable solution to common problems in software design; improves code quality, maintainability, and communication.

52. **Name common design patterns.**
    - Answer: Singleton, Factory, Builder, Adapter, Decorator, Observer, Strategy, Template Method, Facade.

53. **What is the Singleton pattern?**
    - Answer: Ensures single instance creation; used for logger, cache, connection pool; thread-safe lazy initialization.

54. **What is the Factory pattern?**
    - Answer: Creates objects without specifying exact classes; useful for object creation logic; decouples client from concrete classes.

55. **What is the Builder pattern?**
    - Answer: Constructs complex objects step-by-step; improves readability; separates construction from representation.

### Intermediate Level

56. **What is the Repository pattern?**
    - Answer: Abstracts data access layer; provides high-level interface for database operations; decouples business logic from persistence.

57. **What is the Service layer pattern?**
    - Answer: Business logic layer between controller and repository; single responsibility; improves testability and reusability.

58. **What is the DTO (Data Transfer Object) pattern?**
    - Answer: Separates API model from domain model; reduces over-fetching/under-fetching; improves security and flexibility.

59. **What are SOLID principles?**
    - Answer:
      - S: Single Responsibility - One reason to change
      - O: Open/Closed - Open for extension, closed for modification
      - L: Liskov Substitution - Subclass substitutes parent
      - I: Interface Segregation - Many small interfaces
      - D: Dependency Inversion - Depend on abstractions

### Advanced Level

60. **What is the Adapter pattern?**
    - Answer: Converts interface of class to client expects; useful for legacy code integration; improves compatibility.

61. **What is the Decorator pattern?**
    - Answer: Adds behavior dynamically without modifying class; more flexible than inheritance; composable functionality.

62. **What is the Observer pattern?**
    - Answer: One-to-many dependency; when subject changes, observers notified; implements loose coupling.

63. **What is the Strategy pattern?**
    - Answer: Encapsulates interchangeable algorithms; selects algorithm at runtime; eliminates conditional logic.

---

## REST API DESIGN

### Beginner Level

64. **What is REST (Representational State Transfer)?**
    - Answer: Architectural style for distributed systems; uses HTTP methods, stateless communication, resource-oriented design.

65. **What are HTTP methods?**
    - Answer: GET (retrieve), POST (create), PUT (replace), PATCH (partial update), DELETE (remove), HEAD, OPTIONS.

66. **What are HTTP status codes?**
    - Answer: 2xx (success), 3xx (redirect), 4xx (client error), 5xx (server error); common: 200, 201, 204, 400, 404, 500.

67. **What is REST API idempotency?**
    - Answer: Same request produces same result; GET, PUT, DELETE are idempotent; POST is not.

### Intermediate Level

68. **How do you handle pagination in REST APIs?**
    - Answer: Use skip/take or limit/offset parameters; return total count and next page link; improves performance.

69. **What is API versioning and strategies?**
    - Answer: Managing multiple API versions; strategies: URL path (/v1/), query parameter (?v=1), header (Accept-Version).

70. **How do you implement authentication in REST APIs?**
    - Answer: JWT (tokens), OAuth2 (third-party), Basic Auth, API keys; store securely; validate on each request.

71. **What is CORS and how to enable it?**
    - Answer: Cross-Origin Resource Sharing; allows cross-origin requests; implement via headers or @CrossOrigin annotation.

### Advanced Level

72. **How do you implement rate limiting?**
    - Answer: Limit requests per time period; prevent abuse; implement via middleware, annotations, or third-party services.

73. **What is API caching strategy?**
    - Answer: Cache responses to reduce server load; use ETag, Last-Modified headers; cache-control headers direct caching.

74. **How do you implement hypermedia in REST APIs?**
    - Answer: Include links to related resources (HATEOAS); enables client navigation without hard-coded URLs.

---

## DATABASE DESIGN & SQL

### Beginner Level

75. **What is database normalization?**
    - Answer: Process of organizing data to minimize redundancy; improves data integrity; reduces storage space.

76. **What are normal forms (1NF, 2NF, 3NF)?**
    - Answer:
      - 1NF: Atomic values, no repeating groups
      - 2NF: 1NF + no partial dependencies
      - 3NF: 2NF + no transitive dependencies

77. **What is a primary key and foreign key?**
    - Answer: Primary key uniquely identifies row; foreign key references primary key in another table; maintains referential integrity.

78. **What is indexing in databases?**
    - Answer: Ordered data structure for faster retrieval; trade-off: slower writes, more storage; create on foreign keys and frequently searched columns.

### Intermediate Level

79. **What is the difference between INNER JOIN, LEFT JOIN, RIGHT JOIN?**
    - Answer: INNER: matching rows only; LEFT: all left + matching right; RIGHT: matching left + all right.

80. **What is a transaction and ACID properties?**
    - Answer:
      - Atomicity: All or nothing
      - Consistency: Valid before and after
      - Isolation: Concurrent transactions don't interfere
      - Durability: Committed data persists

81. **What is query optimization?**
    - Answer: Improve query performance; use indexes, avoid N+1, optimize joins, use EXPLAIN to analyze execution.

---

## PROJECT-SPECIFIC QUESTIONS

82. **Explain the database design of this Employee Management System.**
    - Answer: 6 entities (Department, Employee, Project, EmployeeProject, Attendance, Leave); proper relationships with FK constraints; indexes on foreign keys.

83. **How would you implement authentication in this system?**
    - Answer: Add User entity with roles; use Spring Security; implement JWT tokens; add @PreAuthorize annotations on controllers.

84. **How would you optimize the GetAllEmployees query?**
    - Answer: Implement pagination (Page<Employee>), add indexes, use @Query with JOIN FETCH to prevent N+1, cache results.

85. **How would you implement email notifications for leave approvals?**
    - Answer: Use JavaMailSender; create email template; send async via ApplicationEventPublisher; handle exceptions gracefully.

---

## PRACTICAL SCENARIOS

### Architecture & Design Questions

**Q: Design an employee search filter with multiple criteria (name, department, salary range)?**
A: Create SearchCriteria class; use Specification<Employee> for dynamic queries; leverage PredicateBuilder for flexible filtering.

**Q: How would you implement soft deletes (mark as deleted, not actually remove)?**
A: Add isDeleted boolean; override repository queries with @Query; add @Where annotation on entity; filter all queries.

**Q: Design a system for employee performance ratings with history?**
A: Create Rating entity with EmployeeId, Period, Score; track history; calculate averages; maintain audit trail.

**Q: How would you migrate this to multi-tenant architecture?**
A: Add TenantId to all entities; implement tenant resolver; filter all queries by TenantId; separate databases or schemas.

**Q: Design a salary review workflow with approval?**
A: Create SalaryReview entity; implement state machine (Pending→Approved→Rejected); add ApprovedById; email notifications.

---

## TESTING QUESTIONS

86. **What is unit testing and why is it important?**
    - Answer: Testing individual units in isolation; catches bugs early; improves code quality; documentation of expected behavior.

87. **What is mocking and why use it?**
    - Answer: Create fake objects simulating real dependencies; isolate code under test; test behavior without external dependencies.

88. **How to test REST endpoints in Spring Boot?**
    - Answer: Use @SpringBootTest with TestRestTemplate or MockMvc; mock dependencies; verify status codes and response bodies.

89. **How to test JPA repositories?**
    - Answer: Use @DataJpaTest; creates in-memory H2 database; test queries against test data; verify database interactions.

90. **What is integration testing?**
    - Answer: Test multiple components together; verify interactions; use @SpringBootTest; slower but comprehensive; tests real scenarios.

---

## TIPS FOR INTERVIEW SUCCESS

1. **Understand the Employee Management System thoroughly**
   - Know the database design, relationships, and constraints
   - Be able to explain architectural decisions
   - Know potential improvements and extensions

2. **Prepare real-world examples**
   - Reference this project when answering theoretical questions
   - Explain how you would implement features
   - Discuss trade-offs and design decisions

3. **Know Spring Boot basics deeply**
   - Auto-configuration mechanism
   - Dependency injection process
   - Transaction management
   - Exception handling

4. **Practice coding**
   - Write queries using Spring Data JPA
   - Create REST endpoints with proper validation
   - Implement service layer logic
   - Write unit tests

5. **Study design patterns**
   - Know when to use each pattern
   - Implement patterns in this project
   - Discuss SOLID principles with examples

6. **Be ready to discuss**
   - Why Spring Boot instead of plain Spring?
   - Why JPA instead of raw SQL?
   - Why this database design?
   - How to improve the system?

---

## Key Takeaways

- **Spring Boot** simplifies development with auto-configuration and embedded servers
- **Spring Data JPA** provides elegant abstraction over database operations
- **Design Patterns** improve code quality and maintainability
- **SOLID Principles** guide better architecture decisions
- **REST APIs** follow conventions for scalable services
- **Database Design** is crucial for performance and data integrity
- **Testing** ensures code quality and catches regressions early

---

**Good Luck with Your Interview! 🚀**
