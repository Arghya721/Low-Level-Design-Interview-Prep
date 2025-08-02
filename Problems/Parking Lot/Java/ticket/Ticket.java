package ticket;

import java.time.LocalDateTime;

import parkingSpot.ParkingSpot;
import java.util.UUID;

public class Ticket {
    private String ticketID;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private ParkingSpot ps;

    public Ticket(ParkingSpot ps){
        this.ps = ps;
        this.ticketID = UUID.randomUUID().toString();
        this.entryTime = LocalDateTime.now(); 
    }

    public void setExitTime() {
        this.exitTime = LocalDateTime.now();
    }

    public String getTicketID() {
        return ticketID;
    }

    public ParkingSpot getParkingSpot() {
        return ps;
    }

    public String toString() {
        return "Ticket{" +
                "ticketID='" + ticketID + '\'' +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                ", parkingSpot=" + ps.getSpotID() +
                '}';
    }

}
