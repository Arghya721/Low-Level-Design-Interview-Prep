package payments;

// Concrete strategy for credit card payment
// Implements specific payment behavior for credit cards
public class CreditCardPayment implements PayInterface {

    @Override
    public void pay() {
        System.out.println("Payment made using Credit Card.");
    }
    
}
