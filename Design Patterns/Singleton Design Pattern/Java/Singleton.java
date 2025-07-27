// Singleton Design Pattern in Java
// Ensures a class has only one instance and provides a global point of access to it
public class Singleton {
    public String data;
    // Private static instance of the Singleton class
    private static Singleton instance = new Singleton("Singleton Data");
    private Singleton(String data) {
        // Private constructor to prevent instantiation
        this.data = data;
    }

    // Public static method to provide access to the instance
    // Ensures that only one instance is created
    public static Singleton getInstance() {
        return instance;
    }
}