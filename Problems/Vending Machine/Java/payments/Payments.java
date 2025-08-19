package payments;


import inventory.Inventory;


public interface Payments {
    void pay(Inventory inventory, int totalAmount);
}