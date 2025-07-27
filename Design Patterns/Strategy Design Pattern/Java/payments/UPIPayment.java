package payments;

// Concrete strategy for UPI payment
// Implements specific payment behavior for UPI transactions
public class UPIPayment implements PayInterface {
    @Override
    public void pay() {
        System.out.println("Payment made using UPI.");
    }
}
