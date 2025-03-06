// Oliver Benjamin
// CSE146
// Homework04

// Generic queue based off of a generic linked list
public class RbtCmds<T> {
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public RbtCmds() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException("Cannot dequeue from an empty queue");
        }
        T data = head.data;
        head = head.next;
        size--;
        if (isEmpty()) {
            tail = null;
        }
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyQueueException("Cannot peek at an empty queue");
        }
        return head.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static class EmptyQueueException extends RuntimeException {
        public EmptyQueueException(String message) {
            super(message);
        }
    }
}
