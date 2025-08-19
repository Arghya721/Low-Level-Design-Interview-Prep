package transaction;

import java.util.HashMap;
import java.util.UUID;

public class Transaction {
    String id;
    HashMap<Integer, Integer> items;

    public Transaction(HashMap<Integer, Integer> itemHashMap){
        this.id = UUID.randomUUID().toString();
        this.items = itemHashMap;
    }

    public String getId(){
        return id;
    }

    public HashMap<Integer, Integer> getItems(){
        return items;
    }

    public void printTransactionDetails(){
        System.out.println("Transaction ID: " + id);
        System.out.println("Items:");
        for (Integer itemId : items.keySet()) {
            System.out.println(" - " + itemId + ": " + items.get(itemId));
        }
    }
}
