import payments.Payments;
import payments.CreditCardPayment;
import payments.UPIPayment;
import payments.PayInterface;

// Client class demonstrating Strategy Design Pattern
// Different payment strategies can be chosen at runtime
class Main {
    public static void main(String[] args) {
        // Creating credit card payment strategy and processing payment
        PayInterface creditCardPayment = new CreditCardPayment();
        Payments payment = new Payments(100, creditCardPayment);
        payment.pay();

        // Creating UPI payment strategy and processing payment
        PayInterface upiPayment = new UPIPayment();
        Payments upiTransaction = new Payments(200, upiPayment);
        upiTransaction.pay();
    }
}
