// Oliver Benjamin
// CSE146
// Homework06

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ShapeTreeManager {

    private static ShapeBST shapeTree = new ShapeBST();
    private static Scanner keyboard = new Scanner(System.in);

    // Main method to run the Shape Tree program loop.
    public static void main(String[] args) {
        System.out.println("Welcome to the Shape Tree!");

        int choice = -1;

        do {
            printMenu();
            choice = getUserChoice(); 

            if (choice == -1) {
                 continue; 
            }

            switch (choice) {
            case 1:
            readFile();
            break;
            case 2:
            printTraversal();
            break;
            case 3:
            addShape();
            break;
            case 4:
            removeShape();
            break;
            case 5:
            searchShape();
            break;
            case 6:
            findMaxAreaShape();
            break;
            case 7:
            removeGreaterThanArea();
            break;
            case 8:
            writeFile();
            break;
        case 0:
            System.out.println("\nGoodbye!");
            break;
                // No default needed here as getUserChoice validates the range 0-8
            }

             if (choice != 0) {
                 System.out.println("\n----------------------------------------");
             }

        } while (choice != 0);

        keyboard.close();
    }


    private static void printMenu() {
        System.out.println("\nEnter 1. To Read a Shape Tree from a File.");
        System.out.println("Enter 2. To Print a Tree Traversal to the   Console");
        System.out.println("Enter 3. To Add a Shape.");
        System.out.println("Enter 4. To Reove a Shape.");
        System.out.println("Enter 5. To Search for a Shape.");
        System.out.println("Enter 6. To Find the Shape with the Max Area.");
        System.out.println("Enter 7. To Remove All Shaes Greater than an Area.");
        System.out.println("Enter 8. To Print Shape Tree to File.");
        System.out.println("Enter 0. To Quit.");
    }

    private static int getUserChoice() {
        int choice = -1;
        System.out.print("\nEnter your choice |  ");
        try {
            choice = keyboard.nextInt();
            if (choice < 0 || choice > 8) {
                 System.out.println("Invalid choice. Please enter a number between 0 and 8.");
                 choice = -1;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
             choice = -1;
        } finally {
             // ALWAYS consume the rest of the line after reading a number or catching error
             keyboard.nextLine();
        }
        return choice;
    }



    private static void readFile() {
        System.out.print("Enter the file's name: ");
        String filename = keyboard.nextLine();
        int shapesAdded = 0;
        int linesSkipped = 0;

        File file = new File(filename);
        if (!file.exists()) {
            System.err.println("Error: File not found '" + filename + "'");
            return;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\t");
                Shape shape = null;

                if (parts.length < 2) {
                     System.err.println("Skipping malformed line (not enough parts): " + line);
                     linesSkipped++;
                     continue;
                }

                String shapeType = parts[0].trim().toLowerCase();

                 try {
                     // Parses shape data from file lines and creates new Shape object
                     if (shapeType.equals("rectangle")) {
                         if (parts.length == 3) {
                             double length = Double.parseDouble(parts[1].trim());
                             double width = Double.parseDouble(parts[2].trim());
                             shape = new Rectangle(length, width);
                         } else {
                              System.err.println("Skipping malformed Rectangle line (wrong number of parts): " + line);
                              linesSkipped++;
                         }
                     } else if (shapeType.equals("circle")) {
                         if (parts.length == 2) {
                            double radius = Double.parseDouble(parts[1].trim());
                            shape = new Circle(radius);
                         } else {
                              System.err.println("Skipping malformed Circle line (wrong number of parts): " + line);
                              linesSkipped++;
                         }
                     } else if (shapeType.equals("right triangle")) {
                          if (parts.length == 3) {
                             double base = Double.parseDouble(parts[1].trim());
                             double height = Double.parseDouble(parts[2].trim());
                             shape = new RightTriangle(base, height);
                         } else {
                              System.err.println("Skipping malformed Right Triangle line (wrong number of parts): " + line);
                              linesSkipped++;
                         }
                     } else {
                          System.err.println("Skipping unknown shape type: " + parts[0] + " in line: " + line);
                          linesSkipped++;
                     }

        if (shape != null) {
                         shapeTree.add(shape);
                         shapesAdded++;
                     }
                 } catch (NumberFormatException e) {
                  System.err.println("Skipping line due to invalid number format: " + line);
                     linesSkipped++;
              } catch (IllegalArgumentException e) {
                      System.err.println("Skipping line due to invalid shape dimension: " + line + " (" + e.getMessage() + ")");
                      linesSkipped++;
                 }
     }

    System.out.println("\nSuccessfully added " + shapesAdded + " shapes from '" + filename + "'.");
            if (linesSkipped > 0) {
                 System.out.println(linesSkipped + " lines were skipped due to errors.");
            }
        System.out.println("\nPrinting after Reading (In-Order):");
        shapeTree.printInOrder();

        } catch (FileNotFoundException e) {
        System.err.println("Error reading file '" + filename + "'. Details: " + e.getMessage());
        }
    }

    private static void printTraversal() {
         System.out.println("\nWhich traversal?");
         System.out.println("Enter 1. For Pre-order.");
         System.out.println("Enter 2. For In-order");
         System.out.println("Enter 3. For Post-order");
         System.out.print("Enter your choice: ");

         int traversalChoice = -1;
         try {
             traversalChoice = keyboard.nextInt();
         } catch (InputMismatchException e) {
             System.out.println("Invalid input.");
             keyboard.nextLine();
             return;
         }
         keyboard.nextLine();

         switch (traversalChoice) {
             case 1:
                 System.out.println("\nPrinting Pre-Order:");
                 shapeTree.printPreOrder();
                 break;
             case 2:
                 System.out.println("\nPrinting In-Order:");
                 shapeTree.printInOrder();
                 break;
             case 3:
                 System.out.println("\nPrinting Post-Order:");
                 shapeTree.printPostOrder();
                 break;
             default:
                 System.out.println("Invalid traversal choice.");
                 break;
         }
    }

     //shape details and adds the shape to tree
     private static void addShape() {
         System.out.print("Enter the type of shape  to add (Circle, Rectangle, or Right Triangle): ");
         String type = keyboard.nextLine().trim().toLowerCase();
         Shape shape = null;
         double val1 = 0, val2 = 0;

         try {
            if (type.equals("circle")) {
                 System.out.print("Enter the radius: ");
                 val1 = keyboard.nextDouble();
                 keyboard.nextLine();  
                 shape = new Circle(val1);
            } else if (type.equals("rectangle")) {
                 System.out.print("Enter the length: ");
                 val1 = keyboard.nextDouble();
                 keyboard.nextLine();  
                 System.out.print("Enter the width: ");
                 val2 = keyboard.nextDouble();
                 keyboard.nextLine();  
                 shape = new Rectangle(val1, val2);
            } else if (type.equals("right triangle")) {
                 System.out.print("Enter the base: ");
                 val1 = keyboard.nextDouble();
                 keyboard.nextLine();  
                 System.out.print("Enter the height: ");


                 val2 = keyboard.nextDouble();
                 keyboard.nextLine();  
                 shape = new RightTriangle(val1, val2);
            } else {
                 System.out.println("Invalid shape type entered.");
                 return;
            }

            shapeTree.add(shape);
            System.out.println("Shape added successfully.");

         } catch (InputMismatchException e) {
             System.out.println("Invalid input.");


             keyboard.nextLine(); 
         } catch (IllegalArgumentException e) {
             System.err.println("Error adding shape: " + e.getMessage());
         }
    }

    // user for shape details and removes the shape from the tree


    private static void removeShape() {
        System.out.print("Enter the type of shape to remove (Circle, Rectangle, or Right Triangle): ");
        String type = keyboard.nextLine().trim().toLowerCase();
        Shape shapeToRemove = null;
        double val1 = 0, val2 = 0;

        try {
           if (type.equals("circle")) {
                System.out.print("Enter the radius: ");
                val1 = keyboard.nextDouble();
                keyboard.nextLine();

                shapeToRemove = new Circle(val1);
           } else if (type.equals("rectangle")) {
                System.out.print("Enter the length: ");
                val1 = keyboard.nextDouble();
                keyboard.nextLine();
                System.out.print("Enter the width: ");
                val2 = keyboard.nextDouble();
                keyboard.nextLine();
                shapeToRemove = new Rectangle(val1, val2);
            } else if (type.equals("right triangle")) {

                System.out.print("Enter the base: ");
                val1 = keyboard.nextDouble();
                keyboard.nextLine();
                    System.out.print("Enter the height: ");

                val2 = keyboard.nextDouble();
                keyboard.nextLine();
                shapeToRemove = new RightTriangle(val1, val2);
            } else {
                System.out.println("Invalid shape type entered.");
                return;
            }

           boolean removed = shapeTree.remove(shapeToRemove);
           if (removed) {
               System.out.println("Shape removed successfully.");
           } else {
               System.out.println("Shape with specified details not found in the tree.");
           }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            keyboard.nextLine();
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid dimensions entered for removal: " + e.getMessage());
        }
    }

    // Prompts user for shape details and searches for the shape in the tree.
    private static void searchShape() {
        System.out.print("Enter the type of shape to Search (Circle, Rectangle, or Right Triangle): ");
        String type = keyboard.nextLine().trim().toLowerCase();
        Shape shapeToSearch = null;
        double val1 = 0, val2 = 0;

        try {
            if (type.equals("circle")) {
                System.out.print("Enter the radius: ");
                val1 = keyboard.nextDouble();
                keyboard.nextLine();
                shapeToSearch = new Circle(val1);
            } else if (type.equals("rectangle")) {
                System.out.print("Enter the length: ");
                val1 = keyboard.nextDouble();
                keyboard.nextLine();
                System.out.print("Enter the width: ");
                val2 = keyboard.nextDouble();
                keyboard.nextLine();
                shapeToSearch = new Rectangle(val1, val2);
            } else if (type.equals("right triangle")) {
                System.out.print("Enter the base: ");
                val1 = keyboard.nextDouble();
                keyboard.nextLine();
                System.out.print("Enter the height: ");
                val2 = keyboard.nextDouble();
                keyboard.nextLine();
                shapeToSearch = new RightTriangle(val1, val2);
            } else {
                System.out.println("Invalid shape type entered.");
                return;
            }

            boolean found = shapeTree.search(shapeToSearch);
            System.out.println("\nWas the shape in the tree? " + found);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
        keyboard.nextLine();
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid dimensions entered for search: " + e.getMessage());
        }
    }

    // prints the shape with the largest area in tree
    private static void findMaxAreaShape() {
        Shape maxShape = shapeTree.findMax();
        if (maxShape != null) {
            System.out.println("\nThe shape with the max area: " + maxShape.toString());
        } else {
            System.out.println("\nThe tree is empty. No maximum shape found.");
        }
    }

    private static void removeGreaterThanArea() {
         System.out.print("Enter the maximum area (shapes with area GREATER than this will be removed): ");
         double maxArea = -1.0;

         try {
             maxArea = keyboard.nextDouble();
             keyboard.nextLine();  

             if (maxArea < 0) {
                 System.out.println("Area cannot be negative.");
                 return;
             }

             shapeTree.removeGreaterThan(maxArea);
             System.out.println("\nShapes with area greater than " + maxArea + " removed (if any).");

            System.out.println("\nPrinting after removal (In-Order):");
             shapeTree.printInOrder();

         } catch (InputMismatchException e) {
             System.out.println("Invalid input.");
             keyboard.nextLine(); 
         }
    }

    // Writes the current tree data to a user-specified file.
    private static void writeFile() {
          System.out.print("Enter the file's name to write to: ");
         String filename = keyboard.nextLine();

         System.out.println("Attempting to write shape tree data to '" + filename + "'...");
         shapeTree.writeToFile(filename);
         System.out.println("File writing process completed (check console for errors if any).");
    }
}