package parkingSpot;

import vehicle.VehicleType;

public class ParkingSpotFactory {
    public static ParkingSpot createParkingSpot(VehicleType vehicleType, String spotID) {
        switch (vehicleType) {
            case CAR:
                return new CarSpot(spotID);
            case MOTORCYCLE:
                return new MotorcycleSpot(spotID);
            case TRUCK:
                return new TruckSpot(spotID);
            default:
                throw new IllegalArgumentException("Invalid Parking Spot Type");
        }
    }
    
}
