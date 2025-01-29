// Oliver Benjamin
// CSE 146
// Homework 00

import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the vector calculator by Oliver Benjamin\nplease enter the size of your first vector: ");
        int size = scanner.nextInt();
        int A1[] = new int[size];
        int A2[] = new int[size];

        // Create and populate vector 1
        for (int i = 0; i < size; i++) {
            System.out.printf("\nenter an integer for slot %s of Vector 1: ", i+1);
            A1[i] = scanner.nextInt();
            System.out.print("Vector 1: ");
            printVector(A1);
        }

        // Create and populate vector 2
        System.out.println("\nplease enter the size of your second vector: ");
        size = scanner.nextInt();
        for (int i = 0; i < size; i++) {
            System.out.printf("\nenter an integer for slot %s of Vector 2: ", i+1);
            A2[i] = scanner.nextInt();
            System.out.print("Vector 2: ");
            printVector(A2);
        }

        scanner.nextLine();

        // main program loop that runs operations
        while (true) {
            System.out.println("\nVectors stored:");
            System.out.print("Vector 1: ");
            printVector(A1);
            System.out.print("Vector 2: ");
            printVector(A2);
        
            System.out.println("\nOptions:\n1: Add\n2: Subtract\n3: Take magnitude of a vector\nType 'q' to quit");
            String response = scanner.nextLine();
        
            if (response.equals("q")) break; // Exit the loop if q is entered
            
            // check if
            switch (response) {
                case "1" -> {
                    if (!checkVectors(A1, A2)) {
                        System.out.println("Sorry, you may not add vectors whose sizes do not match!");
                        break;
                    }
                    System.out.println("Result: " + Arrays.toString(add(A1, A2)));
                }
                case "2" -> {
                    if (!checkVectors(A1, A2)) {
                        System.out.println("Sorry, you may not subtract vectors whose sizes do not match!");
                        break;
                    }
                    System.out.println("Result: " + Arrays.toString(subtract(A1, A2)));
                }
                case "3" -> {
                    System.out.println("\nDo you need the magnitude of vector 1 or vector 2?\nType 1 for vector 1\nType 2 for vector 2");
                    response = scanner.nextLine();
                    if (response.equals("1")) {
                        System.out.println("Magnitude of Vector 1: " + magnitude(A1));
                    } else if (response.equals("2")) {
                        System.out.println("Magnitude of Vector 2: " + magnitude(A2));
                    } else {
                        System.out.println("Please input 1 or 2. Invalid response.");
                    }
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
        

    // prints vector arg
    public static void printVector(int[] A1) {
        for (Object obj : A1) {
            System.out.printf("[%s]", obj);
        }
        System.out.print("\n");
    }

    // checks if vectors are equal length
    public static boolean checkVectors(int []A1, int [] A2) {
        return A1.length == A2.length;
    }

    // adds two vectors
    public static int[] add(int A1[], int A2[]) {

        int A3[] = new int[A1.length];
        for (int x = 0; x < A1.length; x++) {
            A3[x] = A1[x] + A2[x];
        }
        return A3;
    }

    // subtracts two vectors
    public static int[] subtract(int A1[], int A2[]) {
        int A3[] = new int[A1.length];
        for (int x = 0; x < A1.length; x++) {
            A3[x] = A1[x] - A2[x];
        }
        return A3;
    }

    // calculates magnitude of arg vector
    public static double magnitude(int A1[]) {
        double temp = 0;
        for (int x = 0; x < A1.length; x++) {
            temp += Math.sqrt(A1[x]*A1[x]);
            
        }
        return temp;
    }
}
