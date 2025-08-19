package foodDeliverySystem;


import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import deliveryAgent.DeliveryAgent;
import order.Order;
import order.STATUS;
import payments.PaymentStrategy;
import restaurant.Item;
import restaurant.Restaurant;
import user.User;

public class FoodDeliverySystem {
    private HashMap<Integer, Restaurant> restaurants;
    private HashMap<Integer, User> users;
    private ConcurrentHashMap<Integer, DeliveryAgent> deliveryAgents;
    private ConcurrentHashMap<String, Order> orders;
    private static volatile FoodDeliverySystem instance;

    private FoodDeliverySystem() {
        restaurants = new HashMap<>();
        users = new HashMap<>();
        deliveryAgents = new ConcurrentHashMap<>();
        orders = new ConcurrentHashMap<>();
    }

    public static FoodDeliverySystem getInstance() {
        if (instance == null) {
            synchronized (FoodDeliverySystem.class) {
                if (instance == null) {
                    instance = new FoodDeliverySystem();
                }
            }
        }
        return instance;
    }

    public void registerRestaurant(Restaurant restaurant) {
        restaurants.put(restaurant.getId(), restaurant);
    }

    public void registerUser(User user) {
        users.put(user.getId(), user);
    }

    public void registerDeliveryAgent(DeliveryAgent deliveryAgent) {
        deliveryAgents.put(deliveryAgent.getId(), deliveryAgent);
    }

    public void openRestaurant(int restaurantId) {
        Restaurant restaurant = restaurants.get(restaurantId);
        if (restaurant != null) {
            restaurant.setOpen(true);
        }
    }

    public void closeRestaurant(int restaurantId) {
        Restaurant restaurant = restaurants.get(restaurantId);
        if (restaurant != null) {
            restaurant.setOpen(false);
        }
    }

    public Order placeOrder(int restaurantId, int userId, List<Item> items) {
        Restaurant restaurant = restaurants.get(restaurantId);
        User user = users.get(userId);
        if (restaurant != null && user != null) {
            // check if the restaurant is open
            if (restaurant.isOpen()) {
                // check if the items are available
                for (Item item : items) {
                    Item menuItem = restaurant.getItem(item.getId());
                    if (menuItem == null || !menuItem.isAvailable()) {
                        throw new IllegalArgumentException("Item not available");
                    }
                }
                Order order = new Order(user, restaurant, items, STATUS.PENDING);
                orders.put(order.getId(), order);
                user.addOrderToHistory(order);
                return order;
            }
        }
        return null;
    }

    public void updateOrderStatus(String orderId, STATUS status) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.setStatus(status);
        }
    }

    public void payment(String orderId, PaymentStrategy payment) {
        Order order = orders.get(orderId);
        if (order != null) {
            // Process payment
            if (payment.executePayment(order.getTotalAmount())) {
                order.setStatus(STATUS.IN_PROGRESS);
            } else {
                throw new IllegalArgumentException("Payment failed");
            }
        }
    }

    public synchronized void assignOrderToDeliveryAgent(String orderId) {
        Order order = orders.get(orderId);
        // check for availability of delivery agents
        for (DeliveryAgent agent : deliveryAgents.values()) {
            if (agent.isAvailable()) {
                agent.assignOrder(order);
                updateOrderStatus(orderId, STATUS.OUT_FOR_DELIVERY);
                return;
            }
        }
        throw new IllegalArgumentException("No Delivery Agent available");
    }

    public void completeOrder(String orderId, int deliveryAgentId) {
        Order order = orders.get(orderId);
        DeliveryAgent agent = deliveryAgents.get(deliveryAgentId);
        if (order != null && agent != null && !agent.isAvailable()) {
            agent.completeOrder();
            updateOrderStatus(orderId, STATUS.COMPLETED);
        } else {
            throw new IllegalArgumentException("Invalid order or delivery agent");
        }
    }

    public void printAvailableRestaurants() {
        for (Restaurant restaurant : restaurants.values()) {
            if (restaurant.isOpen()) {
                System.out.println("Available Restaurant: " + restaurant.getName() + " (ID: " + restaurant.getId() + ")");
            }
        }
    }

    public void printRestaurantMenu(int restaurantId) {
        Restaurant restaurant = restaurants.get(restaurantId);
        if (restaurant != null) {
            System.out.println("Menu for Restaurant: " + restaurant.getName());
            for (Item item : restaurant.getMenu()) {
                System.out.println(" - " + item.getName() + ": " + item.getPrice());
            }
        } else {
            System.out.println("Restaurant not found.");
        }
    }

    public void cancelOrder(String orderId) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.setStatus(STATUS.CANCELLED);
        } else {
            throw new IllegalArgumentException("Order not found");
        }
    }
}
