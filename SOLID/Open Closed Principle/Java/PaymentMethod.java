// PaymentMethod interface defines the contract for payment methods
interface PaymentMethod {
    void pay();
}

// CreditCard implements the PaymentMethod interface
class CreditCard implements PaymentMethod {
    @Override
    public void pay() {
        System.out.println("Paid using Credit Card");
    }
}

// PayPal implements the PaymentMethod interface
class PayPal implements PaymentMethod {
    @Override
    public void pay() {
        System.out.println("Paid using PayPal");
    }
}

// BankTransfer implements the PaymentMethod interface
class BankTransfer implements PaymentMethod {
    @Override
    public void pay() {
        System.out.println("Paid using Bank Transfer");
    }
}

// CryptoPayment implements the PaymentMethod interface
class CryptoPayment implements PaymentMethod {
    @Override
    public void pay() {
        System.out.println("Paid using Cryptocurrency");
    }
}

// ProcessPayment uses the PaymentMethod interface
// This class is closed for modification but open for extension
class ProcessPayment {
    public void processPayment(PaymentMethod paymentMethod) {
        paymentMethod.pay();
    }
}

// Main class to demonstrate OCP
public class PaymentMethod {
    public static void main(String[] args) {
        ProcessPayment processor = new ProcessPayment();
        
        // Using different payment methods
        PaymentMethod creditCard = new CreditCard();
        PaymentMethod payPal = new PayPal();
        PaymentMethod bankTransfer = new BankTransfer();
        PaymentMethod crypto = new CryptoPayment();
        
        System.out.println("Open/Closed Principle (OCP) Example");
        System.out.println("===================================");
        
        // Process payments using different methods
        System.out.println("Processing payments:");
        processor.processPayment(creditCard);
        processor.processPayment(payPal);
        processor.processPayment(bankTransfer);
        processor.processPayment(crypto);
        
        System.out.println("\n=== SUMMARY ===");
        System.out.println("OCP Compliance: New payment methods can be added without modifying existing code");
        System.out.println("Benefits: Extensibility, maintainability, and flexibility");
    }
} 