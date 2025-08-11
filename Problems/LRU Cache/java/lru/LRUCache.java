package lru;

public interface LRUCache {
    int get(int key);
    void put(int key, int value);
    int getSize();
    boolean isEmpty();
}
