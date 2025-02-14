// Oliver Benjamin
// CSE 146
// Lab02

import java.io.*;
import java.util.*;

public class FileIOSolutions {
    
    // takes the given text file name and printWrites to the name of the file you want to output
    public static void pastTense(String givenFile, String outputFile) {
        // tries to find input and output file, if fails returns error
        try (Scanner scanner = new Scanner(new File(givenFile));

             
        PrintWriter writer = new PrintWriter(new File(outputFile))) {
            
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (word.equalsIgnoreCase("is")) {
                    word = "was";
                }
                System.out.println(word);
                writer.println(word);
            }
            
        } catch (Exception e) {
            System.out.println("Can't find " + givenFile + "is it in the home directory?");
        }
    }
    
    // Splits volume file into strings, splits lines into 3 parts and calculates the total volume from the integers in those strings
    public static double totalTubeVolume(String givenFile) {
        double addedVol = 0;
        try (Scanner scanner = new Scanner(new File(givenFile))) {
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split("\t");
                
                
                    // tries to parse the data if it fails it ignores the data because it's invalid
                    try {
                        double radius = Double.parseDouble(data[1]);
                        double height = Double.parseDouble(data[2]);
                        
                        if (radius > 0 && height > 0) {
                            double volume = Math.PI * Math.pow(radius, 2) * height;
                            addedVol += volume;
                        }
                    
                    } catch (Exception e) {
                    }
            }
            
        } catch (Exception e) {
            System.out.println("Can't find " + givenFile + "is it in the home directory?");
        }
        return addedVol;
    }
}
