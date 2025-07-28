package vehicle;

// Suzuki is a concrete implementation of the Vehicle interface
public class Suzuki implements Vehicle {
    @Override
    public void averageMileage() {
        System.out.println("Average mileage of Suzuki is 18 km/l");
    }

}
