package order;

import java.util.List;
import java.util.UUID;

import restaurant.Item;
import restaurant.Restaurant;
import user.User;

public class Order {
    private String id;
    private User user;
    private Restaurant restaurant;
    private List<Item> orderedItems;
    private STATUS status;

    public Order(User user, Restaurant restaurant, List<Item> orderedItems, STATUS status) {
        this.id = UUID.randomUUID().toString(); // Generate a unique ID for the order
        this.user = user;
        this.restaurant = restaurant;
        this.orderedItems = orderedItems;
        this.status = status;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Item> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<Item> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
        this.user.notifyOrderStatusUpdate(this);
    }
    
    public double getTotalAmount() {
        return orderedItems.stream().mapToDouble(Item::getPrice).sum();
    }
}
