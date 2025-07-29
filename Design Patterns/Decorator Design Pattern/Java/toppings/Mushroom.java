package toppings;
import basePizza.BasePizzaInterface;

// Mushroom topping class implementing ToppingInterface
public class Mushroom implements ToppingInterface {

    // Instance variable to hold the base pizza
    private BasePizzaInterface basePizza;

    // Constructor to initialize the base pizza
    public Mushroom(BasePizzaInterface basePizza) {
        this.basePizza = basePizza;
    }

    // Method to calculate the total cost including the Mushroom topping
    @Override
    public int cost() {
        return basePizza.cost() + 30;
    }
    
}
