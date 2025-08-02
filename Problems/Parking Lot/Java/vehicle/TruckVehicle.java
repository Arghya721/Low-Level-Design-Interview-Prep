package vehicle;

public class TruckVehicle implements Vehicle {
    private String vehicleNumber; 
    private VehicleType vehicleType;
    
    public TruckVehicle(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = VehicleType.TRUCK;
    }

    @Override
    public VehicleType getVehicleType() {
        return this.vehicleType;
    }

    @Override
    public String getVehicleNumber() {
        return this.vehicleNumber;
    }
}
