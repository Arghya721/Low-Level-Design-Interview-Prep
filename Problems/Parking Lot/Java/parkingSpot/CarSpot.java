package parkingSpot;

import vehicle.Vehicle;
import vehicle.VehicleType;

public class CarSpot implements ParkingSpot {
    private String spotID;
    private VehicleType spotType;
    private boolean isEmpty;
    private Vehicle vehicle;

    public CarSpot(String spotID){
        this.spotID = spotID;
        spotType = VehicleType.CAR;
        isEmpty = true;
    }

    @Override
    public String getSpotID() {
        return spotID;
    }

    @Override
    public VehicleType getSpotType(){
        return spotType;
    }

    @Override
    public boolean isSpotEmpty(){
        return isEmpty;
    }

    @Override
    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public void parkVehicle(Vehicle v){
        vehicle = v;
        isEmpty = false;
    }

    @Override
    public void unParkVehicle(){
        vehicle = null;
        isEmpty = true;
    }
}
