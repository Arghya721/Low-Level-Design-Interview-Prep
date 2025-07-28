public class Main {
    public static void main(String[] args) {
        // Create a Student object using the Builder pattern
        Student student = new Student.StudentBuilder("John", "Doe")
                // .age(20)
                .email("john.doe@example.com")
                .phoneNumber("123-456-7890")
                .build();
        // Print the Student details
        System.out.println(student.toString());
    }
}
