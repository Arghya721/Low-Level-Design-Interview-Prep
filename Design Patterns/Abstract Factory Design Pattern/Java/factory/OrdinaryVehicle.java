package factory;

import vehicle.Vehicle;
import vehicle.Suzuki;
import vehicle.Hyundai;

// OrdinaryVehicle is a concrete implementation of the VehicleFactory interface
public class OrdinaryVehicle implements VehicleFactory {
    @Override
    public Vehicle getVehicle(String vehicleType) {
        switch (vehicleType) {
            case "Suzuki":
                System.out.println("Creating a Suzuki ordinary vehicle.");
                return new Suzuki();
            case "Hyundai":
                System.out.println("Creating a Hyundai ordinary vehicle.");
                return new Hyundai();
            default:
                System.out.println("Unknown ordinary vehicle type: " + vehicleType);
                return null;
        }
    }
    
}
