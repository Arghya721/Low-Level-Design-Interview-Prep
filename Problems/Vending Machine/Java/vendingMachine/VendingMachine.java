package vendingMachine;

import java.util.Map;

import inventory.Inventory;
import payments.Payments;
import transaction.Transaction;
import product.Product;

public class VendingMachine {
    private static volatile VendingMachine instance;
    private final Inventory inventory;

    private VendingMachine(Inventory inventory){
        this.inventory = inventory;
    }

    public synchronized static VendingMachine getInstance(Inventory inventory){
        if (instance == null){
            instance = new VendingMachine(inventory);
        }

        return instance;
    }

    public synchronized void makeOrder(Transaction transaction, Payments payment) throws Exception {
        // verify quantiy and product available in inventory
        for(Map.Entry<Integer, Integer> entrySet : transaction.getItems().entrySet()){
            if(inventory.getStock().containsKey(entrySet.getKey())){
                Product product = inventory.getStock().get(entrySet.getKey());
                if(product.getQuantity() < entrySet.getValue()){
                    throw new Exception("Given quantity is not avaiable");
                }
            } else {
                throw new Exception("Product not available");
            }
        }

        // check for total amount
        int totalAmount = 0;
        for (Map.Entry<Integer, Integer> entry : transaction.getItems().entrySet()) {
            Product product = inventory.getStock().get(entry.getKey());
            if (product != null) {
                totalAmount += product.getPrice() * entry.getValue();
            }
        }

        // ask for payments 
        payment.pay(inventory, totalAmount);

        // update inventory
        for (Map.Entry<Integer, Integer> entry : transaction.getItems().entrySet()) {
            Product product = inventory.getStock().get(entry.getKey());
            if (product != null) {
                product.setQuantity(product.getQuantity() - entry.getValue());
            }
        }

        // Print the bill
        System.out.println("Bill:");
        System.out.println("-------------------");
        for (Map.Entry<Integer, Integer> entry : transaction.getItems().entrySet()) {
            Product product = inventory.getStock().get(entry.getKey());
            if (product != null) {
                System.out.println(product.getName() + " x " + entry.getValue() + " = " + (product.getPrice() * entry.getValue()));
            }
        }
        System.out.println("Total: " + totalAmount);

    }

    public void printInventory() {
        inventory.printInventory();
    }
}
