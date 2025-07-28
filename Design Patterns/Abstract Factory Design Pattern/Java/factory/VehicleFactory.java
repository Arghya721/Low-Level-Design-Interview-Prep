package factory;
import vehicle.Vehicle;

// VehicleFactory interface defines a method to get a vehicle based on its type
public interface VehicleFactory {
    Vehicle getVehicle(String vehicleType);
}
