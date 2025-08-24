import java.util.List;

import deliveryAgent.DeliveryAgent;
import foodDeliverySystem.FoodDeliverySystem;
import order.Order;
import payments.OnlinePaymentStrategy;
import payments.PaymentStrategy;
import restaurant.Item;
import restaurant.Restaurant;
import user.User;

public class Main {
    public static void main(String[] args) {
        FoodDeliverySystem system = FoodDeliverySystem.getInstance();

        Item item1 = new Item(1, "Margherita Pizza", 8.99, true);
        Item item2 = new Item(2, "Pepperoni Pizza", 9.99, true);

        Restaurant restaurant1 = new Restaurant(1, "Pizza Place", List.of(item1, item2), true);

        Item item3 = new Item(3, "Caesar Salad", 5.99, true);
        Item item4 = new Item(4, "Garlic Bread", 3.99, true);

        Restaurant restaurant2 = new Restaurant(2, "Salad Place", List.of(item3, item4), true);

        system.registerRestaurant(restaurant1);
        system.registerRestaurant(restaurant2);

        User user = new User(1, "John Doe", "123 Main St, Springfield, USA");
        system.registerUser(user);

        DeliveryAgent agent1 = new DeliveryAgent(1, "Agent Smith");
        system.registerDeliveryAgent(agent1);

        try {
            Order order1 = system.placeOrder(user.getId(), restaurant1.getId(), List.of(item1, item2));

            // make payment
            PaymentStrategy payment = new PaymentStrategy(new OnlinePaymentStrategy());
            system.payment(order1.getId(), payment);

            // assign order to delivery agent
            system.assignOrderToDeliveryAgent(order1.getId());

            // complete order
            system.completeOrder(order1.getId(), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
