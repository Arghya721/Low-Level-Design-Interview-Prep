package vehicle;

public class VehicleFactory {
    public static Vehicle createVehicle(VehicleType vehicle, String vehicleNumber) {
        switch(vehicle) {
            case VehicleType.CAR:
                return new CarVehicle(vehicleNumber);
            case VehicleType.MOTORCYCLE:
                return new MotorcycleVehicle(vehicleNumber);
            case VehicleType.TRUCK:
                return new TruckVehicle(vehicleNumber); 
            default:
                throw new IllegalArgumentException("Invalid Vehicle Type");
        }
    }
}
