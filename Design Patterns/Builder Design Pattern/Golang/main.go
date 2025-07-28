package main

import "fmt"

// User represents a user with various attributes.
type User struct {
	// Compulsory fields
	FirstName string
	LastName  string

	// Optional fields
	Age         int
	Email       string
	PhoneNumber string
}

type UserBuilder struct {
	firstName   string
	lastName    string
	age         int
	email       string
	phoneNumber string
}

func toString(u *User) string {
	if u == nil {
		return "nil"
	}
	return "User{" +
		"FirstName: " + u.FirstName +
		", LastName: " + u.LastName +
		", Age: " + fmt.Sprint(u.Age) +
		", Email: " + u.Email +
		", PhoneNumber: " + u.PhoneNumber +
		"}"
}

// NewUserBuilder constructor initializes a UserBuilder with compulsory fields.
func NewUserBuilder(firstName, lastName string) *UserBuilder {
	return &UserBuilder{
		firstName: firstName,
		lastName:  lastName,
	}
}

// Setters for optional fields in the UserBuilder.
func (b *UserBuilder) SetAge(age int) *UserBuilder {
	b.age = age
	return b
}

func (b *UserBuilder) SetEmail(email string) *UserBuilder {
	b.email = email
	return b
}

func (b *UserBuilder) SetPhoneNumber(phoneNumber string) *UserBuilder {
	b.phoneNumber = phoneNumber
	return b
}

// Build constructs the User object from the UserBuilder.
func (b *UserBuilder) Build() *User {
	return &User{
		FirstName:   b.firstName,
		LastName:    b.lastName,
		Age:         b.age,
		Email:       b.email,
		PhoneNumber: b.phoneNumber,
	}
}

func main() {
	// Example usage of the UserBuilder
	user := NewUserBuilder("John", "Doe").
		SetAge(30).
		SetEmail("john.doe@example.com").
		SetPhoneNumber("123-456-7890").
		Build()

	fmt.Println(toString(user))
}
