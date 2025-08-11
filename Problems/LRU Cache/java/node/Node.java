package node;

public class Node {
    private int key;
    private int value;
    private Node prev;
    private Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public void setPrev(Node node) {
        this.prev = node;
    }

    public void setNext(Node node) {
        this.next = node;
    }

    public int getKey() {
        return this.key;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getPrev() {
        return this.prev;
    }

    public Node getNext() {
        return this.next;
    }
}
