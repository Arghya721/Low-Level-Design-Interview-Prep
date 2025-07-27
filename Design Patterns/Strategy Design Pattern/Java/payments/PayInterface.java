package payments;

// Strategy interface defining common behavior for all payment methods
// Allows different payment algorithms to be used interchangeably
public interface PayInterface {
    void pay();
}
