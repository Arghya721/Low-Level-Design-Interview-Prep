import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// User entity - only contains data
class User {
    private String id;
    private String name;
    private String email;
    
    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    // Getters only - no business logic
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    
    @Override
    public String toString() {
        return "User{id='" + id + "', name='" + name + "', email='" + email + "'}";
    }
}

// ===== VIOLATES SRP =====
// UserManager has multiple responsibilities
class UserManager {
    private List<User> users = new ArrayList<>();
    
    public void addUser(User user) {
        users.add(user);
        System.out.println("Added user: " + user.getName());
    }
    
    public void saveToDatabase(User user) {
        // Simulate database save
        System.out.println("Saving user " + user.getName() + " to database...");
        try {
            Thread.sleep(100); // Simulate DB operation
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("User " + user.getName() + " saved successfully!");
    }
    
    public void generateUserReport(User user) {
        // Simulate report generation
        System.out.println("Generating report for user: " + user.getName());
        try {
            Thread.sleep(200); // Simulate report generation
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Report generated for user " + user.getName());
    }
}

// ===== FOLLOWS SRP =====
// UserService focuses only on user business logic
class UserService {
    private UserRepository repository;
    
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    
    public void addUser(User user) throws IllegalArgumentException {
        // Validate user data
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be empty");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("User email cannot be empty");
        }
        
        System.out.println("Adding user: " + user.getName());
        repository.save(user);
    }
    
    public User findUserById(String id) {
        return repository.findById(id);
    }
    
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}

// UserRepository focuses only on data persistence
class UserRepository {
    private List<User> users = new ArrayList<>();
    
    public void save(User user) {
        System.out.println("Saving user " + user.getName() + " to database...");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        users.add(user);
        System.out.println("User " + user.getName() + " saved successfully!");
    }
    
    public User findById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
    
    public List<User> findAll() {
        return new ArrayList<>(users);
    }
}

// UserReportGenerator focuses only on report generation
class UserReportGenerator {
    private UserRepository repository;
    
    public UserReportGenerator(UserRepository repository) {
        this.repository = repository;
    }
    
    public void generateReport(User user) {
        System.out.println("Generating detailed report for user: " + user.getName());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Generate report content
        String report = String.format("""
            === User Report ===
            ID: %s
            Name: %s
            Email: %s
            Generated: %s
            ================
            """, user.getId(), user.getName(), user.getEmail(), 
            LocalDateTime.now().toString());
        
        System.out.print(report);
    }
    
    public void generateAllUsersReport() {
        List<User> users = repository.findAll();
        System.out.println("Generating report for all " + users.size() + " users...");
        
        for (User user : users) {
            generateReport(user);
        }
    }
}

// Main class to demonstrate SRP
public class User {
    public static void demonstrateSRPViolation() {
        System.out.println("\n=== DEMONSTRATING SRP VIOLATION ===");
        UserManager userManager = new UserManager();
        
        User user = new User("1", "John Doe", "john@example.com");
        
        // UserManager handles multiple responsibilities
        userManager.addUser(user);
        userManager.saveToDatabase(user);
        userManager.generateUserReport(user);
    }
    
    public static void demonstrateSRPCompliance() {
        System.out.println("\n=== DEMONSTRATING SRP COMPLIANCE ===");
        
        // Create dependencies
        UserRepository repo = new UserRepository();
        UserService userService = new UserService(repo);
        UserReportGenerator reportGenerator = new UserReportGenerator(repo);
        
        // Add users
        User[] users = {
            new User("1", "Alice Johnson", "alice@example.com"),
            new User("2", "Bob Smith", "bob@example.com"),
            new User("3", "Charlie Brown", "charlie@example.com")
        };
        
        for (User user : users) {
            try {
                userService.addUser(user);
            } catch (IllegalArgumentException e) {
                System.err.println("Error adding user: " + e.getMessage());
            }
        }
        
        // Generate reports
        System.out.println("\n--- Individual User Reports ---");
        for (User user : users) {
            reportGenerator.generateReport(user);
        }
        
        System.out.println("\n--- All Users Report ---");
        reportGenerator.generateAllUsersReport();
    }
    
    public static void main(String[] args) {
        System.out.println("Single Responsibility Principle (SRP) Example");
        System.out.println("=============================================");
        
        // Demonstrate both approaches
        demonstrateSRPViolation();
        demonstrateSRPCompliance();
        
        System.out.println("\n=== SUMMARY ===");
        System.out.println("SRP Violation: UserManager handles user management, database operations, and reporting");
        System.out.println("SRP Compliance: Separate classes for UserService (business logic), UserRepository (data access), and UserReportGenerator (reporting)");
    }
} 