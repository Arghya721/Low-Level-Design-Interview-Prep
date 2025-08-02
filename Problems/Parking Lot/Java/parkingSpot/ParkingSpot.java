package parkingSpot;

import vehicle.Vehicle;
import vehicle.VehicleType;

public interface ParkingSpot {
    String getSpotID();
    VehicleType getSpotType();
    boolean isSpotEmpty();
    Vehicle getVehicle();
    void parkVehicle(Vehicle v);
    void unParkVehicle();
}
