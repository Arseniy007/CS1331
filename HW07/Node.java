public class Node<T> {
    // Instance variables
    private T data;
    private Node<T> next;

    // Constructors
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public Node(T data) {
        this(data, null);
    }

    // Getters
    public T getData() {
        return this.data;
    }

    public Node<T> getNext() {
        return this.next;
    }

    // Setters
    public void setData(T data) {
        this.data = data;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
