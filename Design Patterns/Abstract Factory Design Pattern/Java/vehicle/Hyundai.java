package vehicle;

// Hyundai is a concrete implementation of the Vehicle interface
public class Hyundai implements Vehicle {
    @Override
    public void averageMileage() {
        System.out.println("Average mileage of Hyundai is 16 km/l");
    }

}
