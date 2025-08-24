package user;

import java.util.ArrayList;
import java.util.List;

import order.Order;

public class User {
    private int id;
    private String name;
    private String address;
    private List<Order> orderHistory;

    public User(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.orderHistory = new ArrayList<>();
    }

    public void addOrderToHistory(Order order) {
        orderHistory.add(order);
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void notifyOrderStatusUpdate(Order order) {
        // Logic to notify user about order status update
        System.out.println("Order status updated to: " + order.getStatus() + " for order ID: " + order.getId());
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
