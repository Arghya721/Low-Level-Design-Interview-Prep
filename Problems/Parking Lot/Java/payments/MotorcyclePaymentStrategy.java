package payments;

import java.time.Duration;

import ticket.Ticket;

public class MotorcyclePaymentStrategy implements Payments {
    private static final double MOTORCYCLE_RATE_PER_HOUR = 1.0; // Example rate per hour for motorcycle parking

    @Override
    public double pay(Ticket ticket) {
        Duration duration = Duration.between(ticket.getEntryTime(), ticket.getExitTime());

        double parkedHours = Math.ceil(duration.toSeconds() / 3600.0);

        return parkedHours * MOTORCYCLE_RATE_PER_HOUR;
    }
    
}