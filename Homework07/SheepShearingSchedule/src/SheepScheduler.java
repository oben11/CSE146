// Oliver Benjamin
// CSE146
// Homework07

import java.io.*;
import java.util.*;


public class SheepScheduler {

    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Sheep Shearing Scheduler!");

        boolean runAgain;
        do {
            System.out.print("Enter the sheep scheduling file name: ");
            String filename = userInput.nextLine();

            Order[] allSheep = readSheepFile(filename);

            if (allSheep != null) {
                runSimulation(allSheep);
            }

            System.out.print("\nWould you like to run another simulation? (yes/no): ");
            String response = userInput.nextLine().trim().toLowerCase();
            runAgain = response.equals("yes");

        } while (runAgain);

        System.out.println("Thank you for using the Sheep Scheduler!");
        userInput.close();
    }

    private static Order[] readSheepFile(String filename) {
        int numSheep = 0;

        try (Scanner counterScanner = new Scanner(new File(filename))) {
             while (counterScanner.hasNextLine()) {
                String line = counterScanner.nextLine();
                if (line.trim().length() > 0 && line.contains("\t")) {
                    numSheep++;
                }
             }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + filename);
            return null;
        }

        if (numSheep == 0) {
        System.out.println("couldn't find any sheep!");
        return new Order[0];
        }

        Order[] sheepArray = new Order[numSheep];
        int index = 0;
        System.out.println("Reading sheep data from: " + filename);

        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine() && index < numSheep) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\t");
                if (parts.length == 3) {
                    try {
                    String name = parts[0].trim();
                    int shearTime = Integer.parseInt(parts[1].trim());
                    int arrivalTime = Integer.parseInt(parts[2].trim());
                    sheepArray[index++] = new Order(name, shearTime, arrivalTime);
                    } catch (Exception e) {
                        System.err.println("Warning: Skipping invalid line: " + line);
                    }
                } else if (line.trim().length() > 0){
                     System.err.println("Warning: Skipping invalid line: " + line);
                }
            }

            if(index != numSheep) {
                sheepArray = Arrays.copyOf(sheepArray, index);
            }

            Arrays.sort(sheepArray, Comparator.comparingInt(Order::getArrivalTime));
            System.out.println("Successfully read " + sheepArray.length + " sheep records.");
            return sheepArray;

        } catch (FileNotFoundException e) {
        System.out.println("Error: File not found during second read - " + filename);
        return null;
        } catch (Exception e) {
        System.out.println("An unexpected error occurred while reading the file: " + e.getMessage());
        e.printStackTrace();
        return null;
        }
    }


    private static void runSimulation(Order[] allSheep) {
        if (allSheep == null || allSheep.length == 0) {
            System.out.println("No sheep data to simulate.");
            return;
        }


        System.out.println("\n--- Shearing Schedule Simulation ---");

        MinHeap<Order> waitHeap = new MinHeap<Order>();

        int currentTime = 0;
        int sheepIndex = 0;
        Order currentSheep = null;
        int finishTime = -1;


        while (sheepIndex < allSheep.length || !waitHeap.isEmpty() || currentSheep != null) {

            while (sheepIndex < allSheep.length && allSheep[sheepIndex].getArrivalTime() <= currentTime) {
                Order arrivingSheep = allSheep[sheepIndex];
                System.out.println("Time " + currentTime + ": " + arrivingSheep.getName() + " arrived.");
                waitHeap.add(arrivingSheep);
                sheepIndex++;
            }

            if (currentSheep != null && currentTime >= finishTime) {
                System.out.println("Time " + currentTime + ": " + currentSheep.getName() + " finished shearing.");
                currentSheep = null;
                finishTime = -1;
            }

            if (currentSheep == null && !waitHeap.isEmpty()) {
                currentSheep = waitHeap.removeMin();
                finishTime = currentTime + currentSheep.getShearTime();
                System.out.println("Time " + currentTime + ": Starting shearing for " + currentSheep.getName()
                        + " (Est. Finish Time: " + finishTime + ")");
            }

             if(currentSheep == null && waitHeap.isEmpty() && sheepIndex < allSheep.length) {
                 int nextArrivalTime = allSheep[sheepIndex].getArrivalTime();
                  if(nextArrivalTime > currentTime) {
                     currentTime = nextArrivalTime;
                     continue;
                  }
             }

            currentTime++;

        }

        System.out.println("\n--- Simulation Complete ---");
    }
}