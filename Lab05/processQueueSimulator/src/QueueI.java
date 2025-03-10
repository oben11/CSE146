// Oliver Benjamin
// CSE146
// Lab05

// Basic public interface to consolodate queue functionality
public interface QueueI<T> {
    void enqueue(T data);
    T dequeue();
    T peek();
    void print();
}