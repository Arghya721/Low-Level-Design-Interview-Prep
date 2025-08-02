package floor;

import java.util.ArrayList;

import parkingSpot.ParkingSpot;

public class Floor {
    private String floorID;
    private ArrayList<ParkingSpot> parkingSpots;
    
    public Floor(String floorID, ArrayList<ParkingSpot> parkingSpots) {
        this.floorID = floorID;
        this.parkingSpots = parkingSpots;
    }

    public String getFloorID(){
        return floorID;
    }

    public ArrayList<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }
}
