package payments;

// Context class that uses a payment strategy
// Delegates payment processing to the chosen strategy
public class Payments implements PayInterface{
    int amount;
    PayInterface paymentMethod; // Strategy object
    
    // Constructor accepts payment strategy at runtime
    public Payments(int amount, PayInterface paymentMethod) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public void pay() {
        System.out.println("Processing payment of amount: " + amount);
        paymentMethod.pay(); // Delegate to strategy
    }

}
