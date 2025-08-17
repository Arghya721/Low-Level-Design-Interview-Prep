package inventory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import payments.Coin;
import payments.Note;
import product.Product;

public class Inventory {
    private HashMap<Integer, Product> stock;
    private HashMap<Coin, Integer> coinsCount;
    private HashMap<Note, Integer> notesCount;

    public Inventory() {
        this.stock = new HashMap<>();
        this.coinsCount = new HashMap<>();
        this.notesCount = new HashMap<>();
    }

    public void setStock(HashMap<Integer, Product> stock){
        this.stock = stock;
    }

    public Map<Integer, Product> getStock() {
        return Collections.unmodifiableMap(stock);
    }

    public void setCoinsCount(HashMap<Coin, Integer> coinsCount){
        this.coinsCount = coinsCount;
    }

    public void setNotesCount(HashMap<Note, Integer> notesCount){
        this.notesCount = notesCount;
    }

    public HashMap<Coin, Integer> getCoinsCount(){
        return coinsCount;
    }

    public HashMap<Note, Integer> getNotesCount(){
        return notesCount;
    }

    public void addCoin(Coin coin, int count) {
        coinsCount.put(coin, coinsCount.getOrDefault(coin, 0) + count);
    }

    public void addNote(Note note, int count) {
        notesCount.put(note, notesCount.getOrDefault(note, 0) + count);
    }

    public void printInventory() {
        System.out.println("Inventory:");
        for (Product product : stock.values()) {
            System.out.println(product.getName() + " - Quantity: " + product.getQuantity() + ", Price: " + product.getPrice());
        }
        System.out.println("Coins: " + coinsCount);
        System.out.println("Notes: " + notesCount);
    }

    public void addProduct(Product product) {
        if (product.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        stock.put(product.getId(), product);
    }
}
