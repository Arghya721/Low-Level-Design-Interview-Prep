package payments;

import java.time.Duration;

import ticket.Ticket;

public class TruckPaymentStrategy implements Payments {
    private static final double TRUCK_RATE_PER_HOUR = 5.0; // Example rate per hour for truck parking

    @Override
    public double pay(Ticket ticket) {
        Duration duration = Duration.between(ticket.getEntryTime(), ticket.getExitTime());

        double parkedHours = Math.ceil(duration.toSeconds() / 3600.0);

        return parkedHours * TRUCK_RATE_PER_HOUR;
    }
    
}
