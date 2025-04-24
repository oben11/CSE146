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
            System.out.print("Enter the Sheep scheduling file name: ");
            String filename = userInput.nextLine();

            Sheep[] allSheep = readSheepFile(filename);

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

    private static Sheep[] readSheepFile(String filename) {
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
        System.out.println("couldn't find any Sheep!");
        return new Sheep[0];
        }

        Sheep[] SheepArray = new Sheep[numSheep];
        int index = 0;
        System.out.println("Reading Sheep data from: " + filename);

        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNextLine() && index < numSheep) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\t");
                if (parts.length == 3) {
                    try {
                    String name = parts[0].trim();
                    int shearTime = Integer.parseInt(parts[1].trim());
                    int arrivalTime = Integer.parseInt(parts[2].trim());
                    SheepArray[index++] = new Sheep(name, shearTime, arrivalTime);
                    } catch (Exception e) {
                        System.err.println("Warning: Skipping invalid line: " + line);
                    }
                } else if (line.trim().length() > 0){
                     System.err.println("Warning: Skipping invalid line: " + line);
                }
            }

            if(index != numSheep) {
                SheepArray = Arrays.copyOf(SheepArray, index);
            }

            Arrays.sort(SheepArray, Comparator.comparingInt(Sheep::getArrivalTime));
            System.out.println("Successfully read " + SheepArray.length + " Sheep records.");
            return SheepArray;

        } catch (FileNotFoundException e) {
        System.out.println("Error: File not found during second read - " + filename);
        return null;
        } catch (Exception e) {
        System.out.println("An unexpected error occurred while reading the file: " + e.getMessage());
        e.printStackTrace();
        return null;
        }
    }


    private static void runSimulation(Sheep[] allSheep) {
        if (allSheep == null || allSheep.length == 0) {
            System.out.println("No Sheep data to simulate.");
            return;
        }


        System.out.println("\n--- Shearing Schedule Simulation ---");

        MinHeap<Sheep> waitHeap = new MinHeap<Sheep>();

        int currentTime = 0;
        int SheepIndex = 0;
        Sheep currentSheep = null;
        int finishTime = -1;


        while (SheepIndex < allSheep.length || !waitHeap.isEmpty() || currentSheep != null) {

            while (SheepIndex < allSheep.length && allSheep[SheepIndex].getArrivalTime() <= currentTime) {
                Sheep arrivingSheep = allSheep[SheepIndex];
                System.out.println("Time " + currentTime + ": " + arrivingSheep.getName() + " arrived.");
                waitHeap.add(arrivingSheep);
                SheepIndex++;
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

             if(currentSheep == null && waitHeap.isEmpty() && SheepIndex < allSheep.length) {
                 int nextArrivalTime = allSheep[SheepIndex].getArrivalTime();
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