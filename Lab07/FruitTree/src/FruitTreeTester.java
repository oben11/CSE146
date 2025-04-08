// Oliver Benjamin
// CSE146
// Lab07

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FruitTreeTester {

    public static void main(String[] args) {
        LinkedBST<Fruit> fruitTree = new LinkedBST<>();
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Welcome to the fruit tree!");
        System.out.println("Please enter a Fruit File Name");
        String filename = keyboard.nextLine();

        populateTreeFromFile(fruitTree, filename);

        System.out.println("\nPrinting the in-order traversal");

        fruitTree.printInOrder();

        System.out.println("\nPrinting the pre-order traversal");

        fruitTree.printPreOrder();

        System.out.println("\nPrinting the post-order traversal");

        fruitTree.printPostOrder();

        Fruit fruitToDelete = new Fruit("Apple", 0.4859853412170728);
        System.out.println("\nDeleting " + fruitToDelete.toString());
        fruitTree.remove(fruitToDelete);

        System.out.println("Printing in-order traversal after deletion");
        fruitTree.printInOrder();

        keyboard.close();
    }

    private static void populateTreeFromFile(LinkedBST<Fruit> tree, String filename) {
        System.out.println("Populating tree from file: " + filename);
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\t");

                if (parts.length == 2) {
                    try {
                        String type = parts[0].trim();
                        double weight = Double.parseDouble(parts[1].trim());

                        Fruit newFruit = new Fruit(type, weight);

            if (!newFruit.getType().equals("apple") || type.equalsIgnoreCase("apple")) {
                if (newFruit.getWeight() > 0.0) {
                     tree.add(newFruit);
              } else {
                 System.out.println("Skipping invalid weight entry: " + line);
               }
             } else {
               System.out.println("Skipping invalid type entry: " + line);
              }
        } catch (NumberFormatException e) {
               System.err.println("Error parsing weight on line: " + line + " - Skipping.");
        } catch (Exception e) {
            System.err.println("Error processing line: " + line + " - Skipping. Details: " + e.getMessage());
        }
                } else {
                    System.err.println("Malformed line: " + line + " - Skipping.");
                }
            }
            System.out.println("Finished populating tree.");
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + filename);
            System.err.println("Please ensure the file is in the project's directory.");
             System.exit(1);
        }
    }
}