import basePizza.FarmHouse;
import basePizza.Margherita;
import toppings.CheeseBurst;
import toppings.Mushroom;
import toppings.ToppingInterface;


public class Main {
    public static void main(String[] args) {
       
        
        // Add Mushroom topping
        ToppingInterface margheritaWithMushroomTopping = new Mushroom(new Margherita());

        
        // Print the total cost
        System.out.println("Total cost of Margherita pizza with Mushroom topping: " + margheritaWithMushroomTopping.cost());

        // Add farmHouse + mushroom + cheeseBurst
        ToppingInterface farmHouseWithMushroomAndCheeseBurst = new CheeseBurst(new Mushroom(new FarmHouse()));

        // Calculate total cost for FarmHouse pizza with Mushroom and CheeseBurst toppings
        System.out.println("Total cost of FarmHouse pizza with Mushroom and CheeseBurst toppings: " + farmHouseWithMushroomAndCheeseBurst.cost());
    }
}