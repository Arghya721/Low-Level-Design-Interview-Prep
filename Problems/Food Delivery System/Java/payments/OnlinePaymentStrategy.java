package payments;

public class OnlinePaymentStrategy implements Payment {
    @Override
    public boolean pay(double amount) {
        // Logic for online payment
        System.out.println("Processing online payment of: " + amount);
        // Assume payment is successful
        System.out.println("Online payment successful.");
        return true;
    }
}
