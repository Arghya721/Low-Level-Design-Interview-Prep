import lru.LRUCache;
import lru.LRUCacheFactory;

public class Main {
    public static void main(String[] args) {
        // Create LRU Cache with capacity 2
        LRUCache cache = LRUCacheFactory.createLRUCache(2);

        // Test the LRU Cache functionality
        cache.put(1, 1);
        System.out.println("Put(1, 1)");
        cache.put(2, 2);
        System.out.println("Put(2, 2)");
        System.out.println("Get(1): " + cache.get(1)); // returns 1
        
        cache.put(3, 3);
        System.out.println("Put(3, 3)");
        System.out.println("Get(2): " + cache.get(2)); // returns -1 (not found)
        
        cache.put(4, 4); // evicts key 1
        System.out.println("Get(1): " + cache.get(1)); // returns -1 (not found)
        System.out.println("Get(3): " + cache.get(3)); // returns 3
        System.out.println("Get(4): " + cache.get(4)); // returns 4
        
    }
}
