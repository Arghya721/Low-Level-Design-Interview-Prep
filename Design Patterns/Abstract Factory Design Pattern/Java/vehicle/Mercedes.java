package vehicle;

// Mercedes is a concrete implementation of the Vehicle interface
public class Mercedes implements Vehicle {
    @Override
    public void averageMileage() {
        System.out.println("Average mileage of Mercedes is 15 km/l");
    }
}
