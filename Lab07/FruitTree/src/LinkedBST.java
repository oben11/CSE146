// Oliver Benjamin
// CSE146
// Lab07

// Basic binary serch tree implementation similar to that in class
public class LinkedBST<T extends Comparable<T>> {

    private static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }


    private Node<T> root;

    public LinkedBST() {
    this.root = null;
    }

    public void add(T data) {
        if (data == null) return;
        root = addRecursive(root, data);
    }

    public void remove(T data) {
        if (data == null) return;
        root = removeRecursive(root, data);
    }

    public boolean search(T data) {
        if (data == null) return false;
        return searchRecursive(root, data);
    }

    public void printPreOrder() {
        printPreOrderRecursive(root);
        System.out.println();
    }

    public void printInOrder() {
        printInOrderRecursive(root);
        System.out.println();
    }

    public void printPostOrder() {
        printPostOrderRecursive(root);
        System.out.println();
    }

    private Node<T> addRecursive(Node<T> current, T data) {
        if (current == null) {
            return new Node<>(data);
        }

        int compare = data.compareTo(current.data);

        if (compare < 0) {
            current.left = addRecursive(current.left, data);
        } else if (compare > 0) {
            current.right = addRecursive(current.right, data);
        } else {
             current.right = addRecursive(current.right, data);
        }
        return current;
    }

    private Node<T> removeRecursive(Node<T> current, T data) {
        if (current == null) {
            return null;
        }

        int compare = data.compareTo(current.data);

        if (compare < 0) {
            current.left = removeRecursive(current.left, data);
        } else if (compare > 0) {
            current.right = removeRecursive(current.right, data);
        } else {
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }

            current.data = findMin(current.right);
            current.right = removeRecursive(current.right, current.data);
        }
        return current;
    }

    private T findMin(Node<T> node) {
        T minVal = node.data;
        while (node.left != null) {
            minVal = node.left.data;
            node = node.left;
        }
        return minVal;
    }

    private boolean searchRecursive(Node<T> current, T data) {
        if (current == null) {
            return false;
        }

        int compare = data.compareTo(current.data);

        if (compare == 0) {
            return true;
        } else if (compare < 0) {
            return searchRecursive(current.left, data);
        } else {
            return searchRecursive(current.right, data);
        }
    }

    private void printPreOrderRecursive(Node<T> node) {
        if (node != null) {
            System.out.println(node.data.toString());
            printPreOrderRecursive(node.left);
            printPreOrderRecursive(node.right);
        }
    }

    private void printInOrderRecursive(Node<T> node) {
        if (node != null) {
            printInOrderRecursive(node.left);
            System.out.println(node.data.toString());
            printInOrderRecursive(node.right);
        }
    }

    private void printPostOrderRecursive(Node<T> node) {
        if (node != null) {
            printPostOrderRecursive(node.left);
            printPostOrderRecursive(node.right);
            System.out.println(node.data.toString());
        }
    }
}