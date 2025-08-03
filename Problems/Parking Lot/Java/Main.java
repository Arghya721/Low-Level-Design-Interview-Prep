import java.util.ArrayList;
import java.util.Arrays;

import floor.Floor;
import parkingLot.ParkingLot;
import parkingSpot.ParkingSpot;
import parkingSpot.ParkingSpotFactory;
import vehicle.Vehicle;
import vehicle.VehicleFactory;
import vehicle.VehicleType;
import ticket.Ticket;

public class Main {
    public static void main(String[] args) {
        
        ParkingSpot carSpot1 = ParkingSpotFactory.createParkingSpot(VehicleType.CAR, "car1");
        ParkingSpot carSpot2 = ParkingSpotFactory.createParkingSpot(VehicleType.CAR, "car2");
        ParkingSpot motorcycle1 = ParkingSpotFactory.createParkingSpot(VehicleType.MOTORCYCLE, "motorcycle1");
        ParkingSpot motorcycle2 = ParkingSpotFactory.createParkingSpot(VehicleType.MOTORCYCLE, "motorcycle2");
        ParkingSpot truck1 = ParkingSpotFactory.createParkingSpot(VehicleType.TRUCK, "truck1");
        ParkingSpot truck2 = ParkingSpotFactory.createParkingSpot(VehicleType.TRUCK, "truck2");

        Floor floor1 = new Floor("Floor1", new ArrayList<>(Arrays.asList(carSpot1, motorcycle1, truck1)));
        Floor floor2 = new Floor("Floor2", new ArrayList<>(Arrays.asList(carSpot2, motorcycle2, truck2)));

        ParkingLot parkingLot = ParkingLot.getParkingLotInstance(new ArrayList<>(Arrays.asList(floor1, floor2)));

        Vehicle car1 = VehicleFactory.createVehicle(VehicleType.CAR, "CAR123");
        Vehicle car2 = VehicleFactory.createVehicle(VehicleType.CAR, "CAR456");
        Vehicle motorcycle1Vehicle = VehicleFactory.createVehicle(VehicleType.MOTORCYCLE, "MOTO123");
        Vehicle motorcycle2Vehicle = VehicleFactory.createVehicle(VehicleType.MOTORCYCLE, "MOTO456");
        Vehicle truck1Vehicle = VehicleFactory.createVehicle(VehicleType.TRUCK, "TRUCK123");
        Vehicle truck2Vehicle = VehicleFactory.createVehicle(VehicleType.TRUCK, "TRUCK456");

        try {
            Ticket car1Ticket = parkingLot.parkVehicle(car1);
            System.out.println("Car 1 parked with ticket: " + car1Ticket.toString());
            Ticket car2Ticket = parkingLot.parkVehicle(car2);
            System.out.println("Car 2 parked with ticket: " + car2Ticket.toString());
            Ticket motorcycle1Ticket = parkingLot.parkVehicle(motorcycle1Vehicle);
            System.out.println("Motorcycle 1 parked with ticket: " + motorcycle1Ticket.toString());
            Ticket motorcycle2Ticket = parkingLot.parkVehicle(motorcycle2Vehicle);
            System.out.println("Motorcycle 2 parked with ticket: " + motorcycle2Ticket.toString());
            Ticket truck1Ticket = parkingLot.parkVehicle(truck1Vehicle);
            System.out.println("Truck 1 parked with ticket: " + truck1Ticket.toString());
            Ticket truck2Ticket = parkingLot.parkVehicle(truck2Vehicle);
            System.out.println("Truck 2 parked with ticket: " + truck2Ticket.toString());

            // wait for some time to simulate parking duration
            Thread.sleep(2000); // Simulating 2 seconds of parking
            
            System.out.println("Cost for Car 1: " + parkingLot.unParkVehicle(car1));            

            Vehicle carExtra = VehicleFactory.createVehicle(VehicleType.CAR, "CAREXTRA");
            Ticket carExtraTicket = parkingLot.parkVehicle(carExtra);
            System.out.println("Extra Car parked with ticket: " + carExtraTicket.toString());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


    }
}
