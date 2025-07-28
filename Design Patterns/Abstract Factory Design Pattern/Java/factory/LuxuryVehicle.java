package factory;

import vehicle.Vehicle;
import vehicle.BMW;
import vehicle.Mercedes;

// LuxuryVehicle is a concrete implementation of the VehicleFactory interface
public class LuxuryVehicle implements VehicleFactory {
    @Override
    public Vehicle getVehicle(String vehicleType) {
        switch (vehicleType) {
            case "BMW":
                System.out.println("Creating a BMW luxury vehicle.");
                return new BMW();
            case "Mercedes":
                System.out.println("Creating a Mercedes luxury vehicle.");
                return new Mercedes();
            default:
                System.out.println("Unknown luxury vehicle type: " + vehicleType);
                return null;
        }
    }
}