package deliveryAgent;

import order.Order;

public class DeliveryAgent {
    private int id;
    private String name;
    private Order order;
    private boolean isAvailable;

    public DeliveryAgent(int id, String name) {
        this.id = id;
        this.name = name;
        this.isAvailable = true; // Default to available
    }

    public int getId() {
        return id;
    }

    public void assignOrder(Order order) {
        this.order = order;
        this.isAvailable = false; // Mark as unavailable when assigned an order
    }

    public void completeOrder() {
        this.order = null;
        this.isAvailable = true; // Mark as available when order is completed
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getName() {
        return name;
    }

    public Order getOrder() {
        return order;
    }
}
