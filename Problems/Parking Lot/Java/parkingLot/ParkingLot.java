package parkingLot;

import java.util.ArrayList;
import java.util.HashMap;

import floor.Floor;
import parkingSpot.ParkingSpot;
import ticket.Ticket;
import vehicle.Vehicle;

public class ParkingLot {
    private static volatile ParkingLot instance;
    private ArrayList<Floor> floors;
    private HashMap<String, Ticket> activeTickets;

    private ParkingLot(ArrayList<Floor> floors) {
        this.floors = floors;
        this.activeTickets = new HashMap<>();
    }

    public synchronized static ParkingLot getParkingLotInstance(ArrayList<Floor> floors) {
        if (instance == null){
            instance = new ParkingLot(floors);
        } 
        return instance;
    }

    public synchronized Ticket parkVehicle(Vehicle vehicle) throws Exception {
        for(Floor floor : floors){
            ArrayList<ParkingSpot> ps = floor.getParkingSpots();
            for(ParkingSpot parkingSpot : ps) {
                if(parkingSpot.isSpotEmpty() && parkingSpot.getSpotType() == vehicle.getVehicleType()){
                    parkingSpot.parkVehicle(vehicle);
                    Ticket ticket = new Ticket(parkingSpot);
                    activeTickets.put(vehicle.getVehicleNumber(), ticket);
                    return ticket;
                }
            }
        }
        throw new Exception("Parking Lot is Full");
    }

    public void unParkVehicle(Vehicle vehicle) throws Exception{
        if(activeTickets.containsKey(vehicle.getVehicleNumber())){
            Ticket ticket = activeTickets.get(vehicle.getVehicleNumber());

            ParkingSpot ps = ticket.getParkingSpot();
            ps.unParkVehicle();
            ticket.setExitTime();
            activeTickets.remove(vehicle.getVehicleNumber());
            System.out.println("Vehicle " + vehicle.getVehicleNumber() + " is unparked");
            return;
        }
        throw new Exception("No such vehicle number found!!");
    }
}
