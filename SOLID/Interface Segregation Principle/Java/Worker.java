// ===== VIOLATES ISP =====
// Worker interface forces all implementations to have both work and eat methods
interface Worker {
    void work();
    void eat();
}

// HumanWorker implements Worker - can both work and eat
class HumanWorker implements Worker {
    private String name;
    
    public HumanWorker(String name) {
        this.name = name;
    }
    
    @Override
    public void work() {
        System.out.println(name + " is working");
    }
    
    @Override
    public void eat() {
        System.out.println(name + " is eating");
    }
}

// RobotWorker violates ISP because it's forced to implement eat() even though robots don't eat
class RobotWorker implements Worker {
    private String name;
    
    public RobotWorker(String name) {
        this.name = name;
    }
    
    @Override
    public void work() {
        System.out.println(name + " is working efficiently");
    }
    
    @Override
    public void eat() {
        // Robots don't eat, but we're forced to implement this method
        System.out.println(name + " doesn't need to eat (but interface forces it)");
    }
}

// ===== FOLLOWS ISP =====
// Separate interfaces for different capabilities
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

// HumanWorkerISP implements both Workable and Eatable
class HumanWorkerISP implements Workable, Eatable {
    private String name;
    
    public HumanWorkerISP(String name) {
        this.name = name;
    }
    
    @Override
    public void work() {
        System.out.println(name + " is working");
    }
    
    @Override
    public void eat() {
        System.out.println(name + " is eating");
    }
}

// RobotWorkerISP implements only Workable - doesn't need to eat
class RobotWorkerISP implements Workable {
    private String name;
    
    public RobotWorkerISP(String name) {
        this.name = name;
    }
    
    @Override
    public void work() {
        System.out.println(name + " is working efficiently");
    }
}

// ===== DEMONSTRATION CLASS =====
public class Worker {
    
    public static void demonstrateISPViolation() {
        System.out.println("\n=== DEMONSTRATING ISP VIOLATION ===");
        
        // Create workers that violate ISP
        Worker human = new HumanWorker("John");
        Worker robot = new RobotWorker("R2D2");
        
        // Both are forced to implement the same interface
        Worker[] workers = {human, robot};
        
        System.out.println("All workers (forced to implement both work and eat):");
        for (Worker worker : workers) {
            worker.work();
            worker.eat();
            System.out.println();
        }
    }
    
    public static void demonstrateISPCompliance() {
        System.out.println("\n=== DEMONSTRATING ISP COMPLIANCE ===");
        
        // Create workers that follow ISP
        Workable human = new HumanWorkerISP("Alice");
        Workable robot = new RobotWorkerISP("C3PO");
        Eatable humanEater = new HumanWorkerISP("Alice");
        
        // Function that works with any workable entity
        System.out.println("Assigning work to workers:");
        assignWork(human);
        assignWork(robot);
        
        // Function that works with any eatable entity
        System.out.println("\nProviding food:");
        provideFood(humanEater);
        // robot cannot be passed to provideFood because it doesn't implement Eatable
        System.out.println("Robot cannot be passed to provideFood function - this is correct!");
    }
    
    public static void demonstrateInterfaceComposition() {
        System.out.println("\n=== DEMONSTRATING INTERFACE COMPOSITION ===");
        
        // Create an array of workable entities
        Workable[] workables = {
            new HumanWorkerISP("Bob"),
            new RobotWorkerISP("WALL-E"),
            new HumanWorkerISP("Charlie")
        };
        
        // All can work
        System.out.println("All workable entities can work:");
        for (Workable worker : workables) {
            worker.work();
        }
        
        // Create an array of eatable entities (only humans)
        Eatable[] eatables = {
            new HumanWorkerISP("David"),
            new HumanWorkerISP("Eve")
        };
        
        // All can eat
        System.out.println("\nAll eatable entities can eat:");
        for (Eatable eater : eatables) {
            eater.eat();
        }
    }
    
    // Helper method that works with any workable entity
    public static void assignWork(Workable worker) {
        System.out.print("Assigning work: ");
        worker.work();
    }
    
    // Helper method that works with any eatable entity
    public static void provideFood(Eatable eater) {
        System.out.print("Providing food: ");
        eater.eat();
    }
    
    public static void main(String[] args) {
        System.out.println("Interface Segregation Principle (ISP) Example");
        System.out.println("============================================");
        
        // Demonstrate ISP violation
        demonstrateISPViolation();
        
        // Demonstrate ISP compliance
        demonstrateISPCompliance();
        
        // Demonstrate interface composition
        demonstrateInterfaceComposition();
        
        System.out.println("\n=== SUMMARY ===");
        System.out.println("ISP Violation: Worker interface forces all implementations to have work() and eat() methods");
        System.out.println("ISP Compliance: Separate interfaces (Workable, Eatable) for different capabilities");
        System.out.println("Benefits: No forced implementations, better separation of concerns, more flexible design");
    }
} 