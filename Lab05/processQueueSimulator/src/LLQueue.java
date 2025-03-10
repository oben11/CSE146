// Oliver Benjamin
// CSE146
// Lab05


// basic linked list/queue impletmentation as shown in class
public class LLQueue<T> implements QueueI<T> {
    private Node<T> head;
    private Node<T> tail;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public LLQueue() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (tail == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    @Override
    public T dequeue() {
        if (head == null) {
            return null;
        }
        T data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return data;
    }

    @Override
    public T peek() {
        if (head == null) {
            return null;
        }
        return head.data;
    }

    @Override
    public void print() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}
