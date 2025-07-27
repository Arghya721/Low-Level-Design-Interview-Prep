package main

import (
	"fmt"
	"log"
	"time"
)

// User represents a user entity
type User struct {
	ID    string
	Name  string
	Email string
}

// ===== VIOLATES SRP =====
// UserManager has multiple responsibilities: user management, database operations, and reporting
type UserManager struct {
	users []User
}

func (u *UserManager) addUser(user User) {
	u.users = append(u.users, user)
	fmt.Printf("Added user: %s\n", user.Name)
}

func (u *UserManager) saveToDatabase(user User) {
	// Simulating database save
	fmt.Printf("Saving user %s to database...\n", user.Name)
	time.Sleep(100 * time.Millisecond) // Simulate DB operation
	fmt.Printf("User %s saved successfully!\n", user.Name)
}

func (u *UserManager) generateUserReport(user User) {
	// Simulating report generation
	fmt.Printf("Generating report for user: %s\n", user.Name)
	time.Sleep(200 * time.Millisecond) // Simulate report generation
	fmt.Printf("Report generated for user %s\n", user.Name)
}

// ===== FOLLOWS SRP =====
// UserService focuses only on user management operations
type UserService struct {
	repo UserRepository
}

func NewUserService(repo UserRepository) *UserService {
	return &UserService{repo: repo}
}

func (u *UserService) addUser(user User) error {
	// Validate user data
	if user.Name == "" || user.Email == "" {
		return fmt.Errorf("invalid user data: name and email are required")
	}

	fmt.Printf("Adding user: %s\n", user.Name)
	return u.repo.Save(user)
}

// UserRepository focuses only on data persistence
type UserRepository struct {
	users []User
}

func (r *UserRepository) Save(user User) error {
	// Simulate database save
	fmt.Printf("Saving user %s to database...\n", user.Name)
	time.Sleep(100 * time.Millisecond)
	r.users = append(r.users, user)
	fmt.Printf("User %s saved successfully!\n", user.Name)
	return nil
}

func (r *UserRepository) FindByID(id string) (User, error) {
	for _, user := range r.users {
		if user.ID == id {
			return user, nil
		}
	}
	return User{}, fmt.Errorf("user not found with ID: %s", id)
}

func (r *UserRepository) GetAll() []User {
	return r.users
}

// UserReportGenerator focuses only on report generation
type UserReportGenerator struct {
	repo UserRepository
}

func NewUserReportGenerator(repo UserRepository) *UserReportGenerator {
	return &UserReportGenerator{repo: repo}
}

func (r *UserReportGenerator) generateReport(user User) error {
	fmt.Printf("Generating detailed report for user: %s\n", user.Name)
	time.Sleep(200 * time.Millisecond)

	// Generate report content
	report := fmt.Sprintf(`
=== User Report ===
ID: %s
Name: %s
Email: %s
Generated: %s
================
`, user.ID, user.Name, user.Email, time.Now().Format("2006-01-02 15:04:05"))

	fmt.Print(report)
	return nil
}

func (r *UserReportGenerator) generateAllUsersReport() error {
	users := r.repo.GetAll()
	fmt.Printf("Generating report for all %d users...\n", len(users))

	for _, user := range users {
		if err := r.generateReport(user); err != nil {
			return err
		}
	}
	return nil
}

// ===== DEMONSTRATION =====
func demonstrateSRPViolation() {
	fmt.Println("\n=== DEMONSTRATING SRP VIOLATION ===")
	userManager := &UserManager{}

	user := User{ID: "1", Name: "John Doe", Email: "john@example.com"}

	// UserManager handles multiple responsibilities
	userManager.addUser(user)
	userManager.saveToDatabase(user)
	userManager.generateUserReport(user)
}

func demonstrateSRPCompliance() {
	fmt.Println("\n=== DEMONSTRATING SRP COMPLIANCE ===")

	// Create dependencies
	repo := &UserRepository{}
	userService := NewUserService(repo)
	reportGenerator := NewUserReportGenerator(repo)

	// Add users
	users := []User{
		{ID: "1", Name: "Alice Johnson", Email: "alice@example.com"},
		{ID: "2", Name: "Bob Smith", Email: "bob@example.com"},
		{ID: "3", Name: "Charlie Brown", Email: "charlie@example.com"},
	}

	for _, user := range users {
		if err := userService.addUser(user); err != nil {
			log.Printf("Error adding user: %v", err)
		}
	}

	// Generate reports
	fmt.Println("\n--- Individual User Reports ---")
	for _, user := range users {
		if err := reportGenerator.generateReport(user); err != nil {
			log.Printf("Error generating report: %v", err)
		}
	}

	fmt.Println("\n--- All Users Report ---")
	if err := reportGenerator.generateAllUsersReport(); err != nil {
		log.Printf("Error generating all users report: %v", err)
	}
}

func main() {
	fmt.Println("Single Responsibility Principle (SRP) Example")
	fmt.Println("=============================================")

	// Demonstrate both approaches
	demonstrateSRPViolation()
	demonstrateSRPCompliance()

	fmt.Println("\n=== SUMMARY ===")
	fmt.Println("SRP Violation: UserManager handles user management, database operations, and reporting")
	fmt.Println("SRP Compliance: Separate types for UserService (business logic), UserRepository (data access), and UserReportGenerator (reporting)")
}
