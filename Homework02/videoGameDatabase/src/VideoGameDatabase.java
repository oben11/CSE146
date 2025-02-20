// Oliver Benjamin
// CSE 146
// Lab03

/*
Write a program in which the user loads and searches a database of video games. 
Users should have the option to search games by their title, their console, or both
based on partial matches. In addition, the user can use the wildcard “*” 
to indicate they want all results from either titles or consoles.
The user should have the option to both print the results of the search 
via the console or print the results to a file.
 */

import java.io.*;
import java.util.*;

public class VideoGameDatabase {

    // initialize linked list classes for each list
    public static LL<String> StringDB = new LL();
    public static LL<CollectionObject> loadedDB = new LL();
    public static LL<CollectionObject>lastSearchResult = new LL();
        // controls user input, main command loop.
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                System.out.println("\nEnter 1 to load the video game database");
                System.out.println("Enter 2 to search the database");
                System.out.println("Enter 3 to print current results to the console");
                System.out.println("Enter 4 to print current results to file");
                System.out.println("Enter 0 to quit");
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                // command choices
                switch (choice) {
                    case 1:
                        System.out.println("Enter the file name");
                        String fileName = scanner.nextLine();
                        loadDatabase(fileName);
                        break;
                    case 2:
                        System.out.println("Enter the name of the game or '*' for all");
                        String gameName = scanner.nextLine();
                        System.out.println("Enter the name of the console or '*' for all");
                        String console = scanner.nextLine();
                        lastSearchResult = searchDatabase(gameName, console);
                        printResults(lastSearchResult);

                        break;
                    case 3:
                        try {
                            printResults(lastSearchResult);

                        } catch (Exception e) {
                            System.out.println("couldn't find last search result...");
                        }
                        break;
                    case 4:
                        System.out.println("Enter the output file name");
                        String outputFile = scanner.nextLine();
                        printResultsToFile(lastSearchResult, outputFile);
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }
        }
        
        // loads database into stringLL then converts to objects, 
        // could be made more efficient by just using one LL and for loop since LL are dynamic.
        private static void loadDatabase(String fileName) {
            System.out.println("Loading database from " + fileName + "...");
            String tempString;
            CollectionObject collectionObject;
            try {
                Scanner fileScanner = new Scanner(new File(fileName));
                while(fileScanner.hasNextLine())
                {
                    StringDB.add(fileScanner.nextLine());
                
            }
        } catch (Exception e) {
            System.out.println("I couldn't find: " + fileName);
        }

        try {
            for (int i = 0; i < StringDB.getSize(); i++) {
                tempString = StringDB.getAt(i).toString();
                collectionObject = new CollectionObject(tempString.split("\t")[0], tempString.split("\t")[1]);
                loadedDB.add(collectionObject);
                System.out.printf("initilized Collection object with %s | %s", tempString.split("\t")[0], tempString.split("\t")[1]);
            }

            
        } catch (Exception e) {
            System.out.println("I couldn't populate: " + fileName);

        }


    }
    
    // searches loadedDB for certain terms or "*" any term, returns LL of results
    private static LL<CollectionObject> searchDatabase(String gameName, String consoleName) {
        System.out.println("Searching for: " + gameName + " on " + consoleName);
        LL<CollectionObject> lastSearchResult = new LL();

        for (int i = 0; i < loadedDB.getSize(); i++) {
            if ((loadedDB.getAt(i).getName().contains(gameName) || gameName.equals("*")) 
            && (loadedDB.getAt(i).getConsole().contains(consoleName) || consoleName.equals("*"))) {
                lastSearchResult.add(loadedDB.getAt(i));
            }
        }
        return lastSearchResult;
    }
    
    // prints lastSearchResult to console.
    private static void printResults(LL<CollectionObject> lastSearchResult) {
        if (lastSearchResult.getSize() == 0) {
            System.out.println("No results found.");
            System.out.println(lastSearchResult.getSize());
        } else {
            for (int i = 0; i < lastSearchResult.getSize(); i++) {
                System.out.printf("GAME %s | Name: %s | Console %s\n", i, lastSearchResult.getAt(i).getName(), lastSearchResult.getAt(i).getConsole());
                
            }
        }
    }

    // prints lastSearchResult to given file.
    private static void printResultsToFile(LL<CollectionObject> lastSearchResult, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (int i = 0; i< lastSearchResult.getSize(); i++) {
                writer.printf("GAME %s | Name: %s | Console %s\n", i, lastSearchResult.getAt(i).getName(), lastSearchResult.getAt(i).getConsole());
            }
            System.out.println("Results written to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}

