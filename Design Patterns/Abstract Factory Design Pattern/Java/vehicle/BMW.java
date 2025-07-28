package vehicle;

// BMW is a concrete implementation of the Vehicle interface
public class BMW implements Vehicle {
    @Override
    public void averageMileage() {
        System.out.println("Average mileage of BMW is 12 km/l");
    }
}
