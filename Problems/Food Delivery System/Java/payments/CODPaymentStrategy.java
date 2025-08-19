package payments;

public class CODPaymentStrategy implements Payment {
    @Override
    public boolean pay(double amount) {
        // Logic for cash on delivery payment
        System.out.println("Processing cash on delivery payment of: " + amount);
        
        return true;
    }
}
