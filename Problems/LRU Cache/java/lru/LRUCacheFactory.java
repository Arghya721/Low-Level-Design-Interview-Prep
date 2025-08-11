package lru;

public class LRUCacheFactory {
    
    public static LRUCache createLRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        return new LRUCacheImpl(capacity);
    }
}
