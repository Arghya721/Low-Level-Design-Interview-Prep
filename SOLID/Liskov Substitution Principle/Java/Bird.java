// ===== VIOLATES LSP =====
// Bird base class with fly method
class Bird {
    protected String name;
    
    public Bird(String name) {
        this.name = name;
    }
    
    public void fly() {
        System.out.println(name + " is flying");
    }
    
    public void eat() {
        System.out.println(name + " is eating");
    }
}

// Ostrich violates LSP because it can't fly but inherits from Bird
class Ostrich extends Bird {
    public Ostrich(String name) {
        super(name);
    }
    
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostriches can't fly!");
    }
}

// ===== FOLLOWS LSP =====
// Bird interface defines basic bird behavior
interface BirdInterface {
    void eat();
}

// FlyingBird interface extends Bird for flying birds
interface FlyingBird extends BirdInterface {
    void fly();
}

// Sparrow implements FlyingBird - can both eat and fly
class Sparrow implements FlyingBird {
    private String name;
    
    public Sparrow(String name) {
        this.name = name;
    }
    
    @Override
    public void eat() {
        System.out.println(name + " is eating seeds");
    }
    
    @Override
    public void fly() {
        System.out.println(name + " is flying high");
    }
}

// OstrichLSP implements only BirdInterface - cannot fly
class OstrichLSP implements BirdInterface {
    private String name;
    
    public OstrichLSP(String name) {
        this.name = name;
    }
    
    @Override
    public void eat() {
        System.out.println(name + " is eating plants");
    }
}

// ===== DEMONSTRATION CLASS =====
public class Bird {
    
    public static void demonstrateLSPViolation() {
        System.out.println("\n=== DEMONSTRATING LSP VIOLATION ===");
        
        // This violates LSP - Ostrich can't fly but inherits from Bird
        Bird ostrich = new Ostrich("Ostrich");
        
        System.out.println("Ostrich eating (works fine):");
        ostrich.eat();
        
        System.out.println("Ostrich trying to fly (will throw exception):");
        try {
            ostrich.fly(); // This will throw UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
    
    public static void demonstrateLSPCompliance() {
        System.out.println("\n=== DEMONSTRATING LSP COMPLIANCE ===");
        
        // Create birds that follow LSP
        FlyingBird sparrow = new Sparrow("Sparrow");
        BirdInterface ostrich = new OstrichLSP("Ostrich");
        
        // Function that works with any bird
        System.out.println("Feeding birds:");
        feedBird(sparrow);
        feedBird(ostrich);
        
        // Function that works with flying birds
        System.out.println("\nFlying birds:");
        flyBird(sparrow);
        // ostrich cannot be passed to flyBird because it doesn't implement FlyingBird
        System.out.println("Ostrich cannot be passed to flyBird function - this is correct!");
    }
    
    public static void demonstrateSubstitution() {
        System.out.println("\n=== DEMONSTRATING PROPER SUBSTITUTION ===");
        
        // Create an array of flying birds
        FlyingBird[] flyingBirds = {
            new Sparrow("Robin"),
            new Sparrow("Blue Jay"),
            new Sparrow("Cardinal")
        };
        
        // All flying birds can be substituted for each other
        System.out.println("All flying birds can fly:");
        for (FlyingBird bird : flyingBirds) {
            bird.fly();
        }
        
        // Create an array of any birds
        BirdInterface[] allBirds = {
            new Sparrow("Sparrow"),
            new OstrichLSP("Ostrich")
        };
        
        // All birds can eat (they all implement BirdInterface)
        System.out.println("\nAll birds can eat:");
        for (BirdInterface bird : allBirds) {
            bird.eat();
        }
    }
    
    // Helper method that works with any bird
    public static void feedBird(BirdInterface bird) {
        System.out.print("Feeding bird: ");
        bird.eat();
    }
    
    // Helper method that works with flying birds
    public static void flyBird(FlyingBird bird) {
        System.out.print("Making bird fly: ");
        bird.fly();
    }
    
    public static void main(String[] args) {
        System.out.println("Liskov Substitution Principle (LSP) Example");
        System.out.println("===========================================");
        
        // Demonstrate LSP violation
        demonstrateLSPViolation();
        
        // Demonstrate LSP compliance
        demonstrateLSPCompliance();
        
        // Demonstrate proper substitution
        demonstrateSubstitution();
        
        System.out.println("\n=== SUMMARY ===");
        System.out.println("LSP Violation: Ostrich inherits from Bird but can't fly, causing runtime exceptions");
        System.out.println("LSP Compliance: Separate interfaces for different bird capabilities");
        System.out.println("Benefits: Type safety, proper substitution, no runtime errors");
    }
} 