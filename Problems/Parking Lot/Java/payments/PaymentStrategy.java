package payments;

import ticket.Ticket;

public class PaymentStrategy implements Payments {
    Payments paymentStrategy;

    public PaymentStrategy(Payments paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    @Override
    public double pay(Ticket ticket) {
        return paymentStrategy.pay(ticket);
    }
    
}
