package toppings;
import basePizza.BasePizzaInterface;

// CheeseBurst topping class implementing ToppingInterface
public class CheeseBurst implements ToppingInterface {
    private BasePizzaInterface basePizza;

    // Constructor to initialize the base pizza
    public CheeseBurst(BasePizzaInterface basePizza) {
        this.basePizza = basePizza;
    }

    // Method to calculate the total cost including the CheeseBurst topping
    @Override
    public int cost() {
        return basePizza.cost() + 40;
    }

}
