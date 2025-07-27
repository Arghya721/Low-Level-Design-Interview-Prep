
public class Main {
    public static void main(String[] args) {
        // Get the singleton instance
        Singleton singletonInstance1 = Singleton.getInstance();
        // Use the singleton instance
        Singleton singletonInstance2 = Singleton.getInstance();
        System.out.println(singletonInstance1 == singletonInstance2); 
        System.out.println(singletonInstance2.data);
    }
}