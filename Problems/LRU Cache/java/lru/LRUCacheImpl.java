package lru;

import java.util.HashMap;
import java.util.Map;
import node.Node;

public class LRUCacheImpl implements LRUCache {
    private final Node head;
    private final Node tail;
    private final int capacity;
    private final Map<Integer, Node> cache;

    public LRUCacheImpl(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        
        // Initialize dummy head and tail nodes
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
    }

    @Override
    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            // Move to front (most recently used)
            removeNode(node);
            addToFront(node);
            return node.getValue();
        }
        return -1;
    }

    @Override
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // Update existing node
            Node node = cache.get(key);
            node.setValue(value);
            removeNode(node);
            addToFront(node);
        } else {
            // Create new node
            if (cache.size() >= capacity) {
                // Remove least recently used (tail)
                Node lruNode = tail.getPrev();
                removeNode(lruNode);
                cache.remove(lruNode.getKey());
            }
            
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToFront(newNode);
        }
    }

    private void addToFront(Node node) {
        Node next = head.getNext();
        head.setNext(node);
        node.setPrev(head);
        node.setNext(next);
        next.setPrev(node);
    }

    private void removeNode(Node node) {
        Node prev = node.getPrev();
        Node next = node.getNext();
        prev.setNext(next);
        next.setPrev(prev);
    }

    public int getSize() {
        return cache.size();
    }

    public boolean isEmpty() {
        return cache.isEmpty();
    }
}
