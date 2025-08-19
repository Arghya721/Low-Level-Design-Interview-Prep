import inventory.Inventory;

import payments.Payments;
import payments.Coin;
import payments.CoinStrategy;
import payments.Note;
import payments.NoteStrategy;
import product.Product;
import transaction.Transaction;
import vendingMachine.VendingMachine;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Product soda = new Product("Soda", 30, 1, 10);
        Product chips = new Product("Chips", 20, 2, 5);
        Product candy = new Product("Candy", 10, 3, 20);

        // Create inventory
        Inventory inventory = new Inventory();
        inventory.setStock(new HashMap<>() {{
            put(soda.getId(), soda);
            put(chips.getId(), chips);
            put(candy.getId(), candy);
        }});
        inventory.setCoinsCount(new HashMap<>() {{
            put(Coin.TEN, 10);
            put(Coin.FIVE, 5);
        }});
        inventory.setNotesCount(new HashMap<>() {{
            put(Note.TEN, 10);
            put(Note.TWENTY, 5);
        }});

        // Create vending machine
        VendingMachine vendingMachine = VendingMachine.getInstance(inventory);

        // Print inventory
        vendingMachine.printInventory();

        // create a transaction
        HashMap<Integer, Integer> itemHashMap = new HashMap<>();
        itemHashMap.put(soda.getId(), 2);
        itemHashMap.put(chips.getId(), 1);
        Transaction transaction = new Transaction(itemHashMap);
        
        // transaction.printTransactionDetails();

        // create a payment
        HashMap<Note, Integer> payment = new HashMap<>();
        payment.put(Note.TWO_HUNDRED, 1);
        // payment.put(Note.TWENTY, 1);
        // payment.put(Coin.FIVE, 1);

        Payments paymentStrategy = new NoteStrategy(payment);

        // process the transaction
        try {
            vendingMachine.makeOrder(transaction, paymentStrategy);
        } catch (Exception e) {
            System.out.println("Error processing transaction: " + e.getMessage());
        }

        inventory.printInventory();

    }
}
