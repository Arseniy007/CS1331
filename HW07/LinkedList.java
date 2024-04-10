import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T>{
    // Instance variables
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    // Getters
    public Node<T> getHead() {
        return this.head;
    }

    public Node<T> getTail() {
        return this.tail;
    }

    // Overwritten methods from List<T> interface
    public void addAtIndex(T data, int index) {
        if (index < 0 || index > this.size) {
            throw new IllegalArgumentException("Your index is out of the list bounds");
        }
        if (data == null) {
            throw new IllegalArgumentException("You cannot add null data to the list");
        }
        Node<T> newNode = new Node<T>(data);
        if (index == 0) {
            newNode.setNext(this.head);
            this.head = newNode;
        }
        else {
            Node<T> currentNode = this.head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }
            Node<T> previousNextNode = currentNode.getNext();
            currentNode.setNext(newNode);
            newNode.setNext(previousNextNode);
        }
        if (index == this.size()) {
            this.tail = newNode;
        }
        this.size++;
    }

    public T getAtIndex(int index) {
        if (index < 0 || index > (this.size() - 1)) {
            throw new IllegalArgumentException("Your index is out of the list bounds");
        }
        if (index == 0) {
            return this.head.getData();
        }
        if (index == (this.size - 1)) {
            return this.tail.getData();
        }
        Node<T> currentNode = this.head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getData();
    }

    public T removeAtIndex(int index) {
        if (index < 0 || index > this.size() - 1) {
            throw new IllegalArgumentException("Your index is out of the list bounds");
        }
        Node<T> nodeToRemove;
        Node<T> currentNode = null;
        if (index == 0) {
            nodeToRemove = this.head;
            this.head = nodeToRemove.getNext();
        }
        else {
            currentNode = this.head;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }
            nodeToRemove = currentNode.getNext();
            currentNode.setNext(nodeToRemove.getNext());
        }
        if (index == (this.size - 1)) {
            this.tail = currentNode;
        }
        this.size--;
        return nodeToRemove.getData();
    }

    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("You cannot remove null data from the list");
        }
        Node<T> currentNode = this.head;
        int index = 0;
        while (currentNode != null) {
            if (currentNode.getData().equals(data)) {
                return removeAtIndex(index);
            }
            currentNode = currentNode.getNext();
            index++;
        }
        throw new NoSuchElementException("The data is not present in the list.");
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.size;
    }
}
