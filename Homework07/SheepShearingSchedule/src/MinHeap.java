// Oliver Benjamin
// CSE146
// Homework07

import java.util.NoSuchElementException;

public class MinHeap<T extends Comparable<T>> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] heap;
    private int size;

    public MinHeap() {
        heap = (T[]) new Comparable[DEFAULT_CAPACITY];
        size = 0;
    }

    public MinHeap(int initialCapacity) {
        if (initialCapacity <= 0) {
            initialCapacity = DEFAULT_CAPACITY;
        }
        heap = (T[]) new Comparable[initialCapacity];
        size = 0;
    }

    public void add(T item) {
        ensureCapacity();
        heap[size] = item;
        percolateUp(size);
        size++;
    }

    public T removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }

        T minItem = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;

        if (size > 0) {
            percolateDown(0);
        }

        return minItem;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == heap.length) {
            resize();
        }
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = heap.length * 2;
        T[] newHeap = (T[]) new Comparable[newCapacity];
        System.arraycopy(heap, 0, newHeap, 0, size);
        heap = newHeap;
    }


    private void percolateUp(int index) {
        T item = heap[index];
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            T parent = heap[parentIndex];
            if (item.compareTo(parent) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void percolateDown(int index) {
        int smallerChildIndex;
        T top = heap[index];

        while (index < size / 2) { // While node has at least one child
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = leftChildIndex + 1;

            if (rightChildIndex < size && heap[rightChildIndex].compareTo(heap[leftChildIndex]) < 0) {
                smallerChildIndex = rightChildIndex;
            } else {
                 if(leftChildIndex < size) { // Check if left child actually exists
                    smallerChildIndex = leftChildIndex;
                 } else {
                    break; // No children exist
                 }
            }


            if (top.compareTo(heap[smallerChildIndex]) > 0) {
                 swap(index, smallerChildIndex);
                 index = smallerChildIndex;
            } else {
                 break; // Found correct position
            }
       }
    }

    private void swap(int index1, int index2) {
        T temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    @Override
    public String toString() {
       if (size == 0) return "[]";
       StringBuilder sb = new StringBuilder("[");
       for (int i = 0; i < size; i++) {
           sb.append(heap[i]);
           if (i < size - 1) {
               sb.append(", ");
           }
       }
       sb.append("]");
       return sb.toString();
   }
}