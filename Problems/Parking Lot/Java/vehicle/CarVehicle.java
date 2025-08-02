package vehicle;

public class CarVehicle implements Vehicle {
    private String vehicleNumber; 
    private VehicleType vehicleType;
    
    public CarVehicle(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = VehicleType.CAR;
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
