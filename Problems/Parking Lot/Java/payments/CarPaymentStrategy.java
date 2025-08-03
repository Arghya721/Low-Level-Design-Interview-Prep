package payments;

import java.time.Duration;

import ticket.Ticket;

public class CarPaymentStrategy implements Payments {
    private static final double CAR_RATE_PER_HOUR = 2.0; // Example rate per hour for car parking

    @Override
    public double pay(Ticket ticket) {
        Duration duration = Duration.between(ticket.getEntryTime(), ticket.getExitTime());

        double parkedHours = Math.ceil(duration.toSeconds() / 3600.0);

        return parkedHours * CAR_RATE_PER_HOUR;
    }
}
