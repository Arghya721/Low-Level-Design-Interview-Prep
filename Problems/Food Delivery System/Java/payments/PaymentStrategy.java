package payments;

public class PaymentStrategy {
    private Payment payment;

    public PaymentStrategy(Payment payment) {
        this.payment = payment;
    }

    public boolean executePayment(double amount) {
        return payment.pay(amount);
    }
}
