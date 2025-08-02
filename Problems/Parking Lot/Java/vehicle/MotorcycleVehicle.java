package vehicle;

public class MotorcycleVehicle implements Vehicle {
    private String vehicleNumber; 
    private VehicleType vehicleType;
    
    public MotorcycleVehicle(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = VehicleType.MOTORCYCLE;
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
