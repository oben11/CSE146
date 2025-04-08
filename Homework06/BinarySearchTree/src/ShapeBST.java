// Oliver Benjamin
// CSE146
// Homework06

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


class ShapeBST {

    private Node root;
    private boolean foundDuringRemove;

    private class Node {
        Shape data;
        Node leftChild;
        Node rightChild;

        Node(Shape data) {
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    public ShapeBST() {
        this.root = null;
    }

    //Add shape
    public void add(Shape shape) {
        if (shape == null) {
             // ignore null shapes
            return;
        }
        this.root = addRecursive(this.root, shape);
    }

    // Recursive add remains standard
    private Node addRecursive(Node current, Shape shape) {
        if (current == null) {
            return new Node(shape); 
        }

        int compareResult = shape.compareTo(current.data);

        if (compareResult < 0) {
            current.leftChild = addRecursive(current.leftChild, shape);
        } else if (compareResult > 0) {
            current.rightChild = addRecursive(current.rightChild, shape);
        }
        // If compareResult == 0, do nothing

        return current; 
    }

// remove  a shape based on its type and specific dimensions using equals().
    public boolean remove(Shape shapeToRemove) {
        if (shapeToRemove == null || root == null) {
            return false; 
        }
        foundDuringRemove = false; 
        this.root = removeRecursive(this.root, shapeToRemove);
        return foundDuringRemove; 
    }

    private Node removeRecursive(Node current, Shape shapeToRemove) {
        if (current == null) {
            return null; 
        }

        int compareResult = shapeToRemove.compareTo(current.data);

        if (compareResult < 0) {
            current.leftChild = removeRecursive(current.leftChild, shapeToRemove);
        } else if (compareResult > 0) {
            current.rightChild = removeRecursive(current.rightChild, shapeToRemove);
        } else {
            if (shapeToRemove.equals(current.data)) {
                foundDuringRemove = true; 

                if (current.leftChild == null && current.rightChild == null) {
                    return null;
                }
                if (current.rightChild == null) {
                    return current.leftChild;
                }
                if (current.leftChild == null) {
                    return current.rightChild;
                }
              
                Shape successorData = findSmallestValue(current.rightChild);
                current.data = successorData;
                current.rightChild = removeSmallestNode(current.rightChild);

            } else {

            }
        }
        return current; 
    }

    private Shape findSmallestValue(Node node) {
        // Go left as far as possible
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node.data;

    }

 
     private Node removeSmallestNode(Node current) {
        if (current == null) return null;

        if (current.leftChild == null) {
            return current.rightChild;
        }

        current.leftChild = removeSmallestNode(current.leftChild);
        return current; 
    }



     public boolean search(Shape shapeToFind) {
         if (shapeToFind == null) return false;
        return searchRecursive(root, shapeToFind);
    }

    private boolean searchRecursive(Node current, Shape shapeToFind) {
        if (current == null) {
            return false;
        }

        int compareResult = shapeToFind.compareTo(current.data);

        if (compareResult < 0) {
            return searchRecursive(current.leftChild, shapeToFind);
        } else if (compareResult > 0) {
            return searchRecursive(current.rightChild, shapeToFind);
        } else {
            // compareResult == 0: Area/Type match. Final check with equals().
            return shapeToFind.equals(current.data);
        }
    }

   
//finds the shape with the maximum area by traversing to the rightmost node.
    public Shape findMax() {
        if (root == null) {
            return null; 
        }
        Node current = root;
        while (current.rightChild != null) {
            current = current.rightChild;
        }
        return current.data;
    }

    //Removes all shapes with area greater than the val
    public void removeGreaterThan(double maxArea) {
        this.root = removeGreaterThanRecursive(this.root, maxArea);
    }

    private Node removeGreaterThanRecursive(Node current, double maxArea) {
        if (current == null) {
            return null; 
        }
        current.leftChild = removeGreaterThanRecursive(current.leftChild, maxArea);
        current.rightChild = removeGreaterThanRecursive(current.rightChild, maxArea);

        if (current.data.getArea() > maxArea) {

             return current.leftChild; 
        } else {
            return current;
        }
    }

    public void printPreOrder() {
        printPreOrderRecursive(root);
        System.out.println(); 
    }

    private void printPreOrderRecursive(Node node) {
        if (node != null) {
            System.out.println(node.data.toString()); 
            printPreOrderRecursive(node.leftChild);    
            printPreOrderRecursive(node.rightChild);  
        }
    }

    public void printInOrder() {
        printInOrderRecursive(root);
         System.out.println(); 
    }

    private void printInOrderRecursive(Node node) {
        if (node != null) {
            printInOrderRecursive(node.leftChild);    
            System.out.println(node.data.toString()); 
            printInOrderRecursive(node.rightChild);   
        }
    }

    public void printPostOrder() {
        printPostOrderRecursive(root);
         System.out.println(); 
    }

    private void printPostOrderRecursive(Node node) {
        if (node != null) {
            printPostOrderRecursive(node.leftChild);    
            printPostOrderRecursive(node.rightChild);   
            System.out.println(node.data.toString()); 
        }
    }

    public void writeToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writeToFileRecursive(root, writer); 
        } catch (IOException e) {
            System.err.println("Error :" + filename + e.getMessage());
        }
    }

    private void writeToFileRecursive(Node node, PrintWriter writer) {
        if (node != null) {
            writeToFileRecursive(node.leftChild, writer);
            writer.println(node.data.getFileString());
            writeToFileRecursive(node.rightChild, writer);
        }
    }
}