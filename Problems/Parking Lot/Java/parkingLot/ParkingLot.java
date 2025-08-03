package parkingLot;

import java.util.ArrayList;
import java.util.HashMap;

import floor.Floor;
import parkingSpot.ParkingSpot;
import payments.CarPaymentStrategy;
import payments.MotorcyclePaymentStrategy;
import payments.Payments;
import payments.TruckPaymentStrategy;
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
        if (instance == null) {
            instance = new ParkingLot(floors);
        }
        return instance;
    }

    public Ticket parkVehicle(Vehicle vehicle) throws Exception {
        for (Floor floor : floors) {
            ArrayList<ParkingSpot> ps = floor.getParkingSpots();
            for (ParkingSpot parkingSpot : ps) {
                synchronized (parkingSpot) {
                    // Check if the parking spot is empty and matches the vehicle type
                    if (parkingSpot.isSpotEmpty() && parkingSpot.getSpotType() == vehicle.getVehicleType()) {
                        parkingSpot.parkVehicle(vehicle);
                        Ticket ticket = new Ticket(parkingSpot);
                        activeTickets.put(vehicle.getVehicleNumber(), ticket);
                        return ticket;
                    }
                }
            }
        }
        throw new Exception("Parking Lot is Full");
    }

    public double unParkVehicle(Vehicle vehicle) throws Exception {
        if (activeTickets.containsKey(vehicle.getVehicleNumber())) {
            Ticket ticket = activeTickets.get(vehicle.getVehicleNumber());

            ParkingSpot ps = ticket.getParkingSpot();
            ps.unParkVehicle();
            ticket.setExitTime();
            activeTickets.remove(vehicle.getVehicleNumber());
            System.out.println("Vehicle " + vehicle.getVehicleNumber() + " is unparked");

            // Calculate payment based on vehicle type
            switch (vehicle.getVehicleType()) {
                case CAR:
                    Payments carPaymentStrategy = new CarPaymentStrategy();
                    return carPaymentStrategy.pay(ticket);
                case MOTORCYCLE:
                    Payments motorcyclePaymentStrategy = new MotorcyclePaymentStrategy();
                    return motorcyclePaymentStrategy.pay(ticket);
                case TRUCK:
                    Payments truckPaymentStrategy = new TruckPaymentStrategy();
                    return truckPaymentStrategy.pay(ticket);
                default:
                    throw new Exception("Unknown vehicle type");
            }
        }
        throw new Exception("No such vehicle number found!!");
    }
}
