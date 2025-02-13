// Oliver Benjamin
// CSE 146
// Homework01

import java.io.*;
import java.util.*;

public class showcaseShowdown {
    public static final String FILE_NAME = "./prizeFile.txt"; 
    public static void main(String[] args) {
        String[] prizeStrings = readFile(FILE_NAME);

        // Counts the number of valid entries by trying to populate each line, if prize.java throws an error, don't count that line.
        int validObjects = 0;
        for (int i = 0; i < prizeStrings.length-1; i++) {
            try {
                new Prize(prizeStrings[i]);
                validObjects++;
            } catch (Exception e) {
                //System.out.println("ignoring line: " + i + " (invalid format)");
            }
        }

        // populates the valid prize objects with the same try catch implmentation
        Prize[] prizeObjects = new Prize[validObjects];
        for (int i = 0; i < validObjects-1; i++) {
            try {
                prizeObjects[i] = new Prize(prizeStrings[i]);

            } catch (Exception e) { 
            }
        }

        // Main loop for actual game
        String quit = "yes";
            while (quit.equalsIgnoreCase("yes")) {
            int totalPrice = 0;
            for (int i = 0; i < 5; i++) {
                Random random = new Random();
                int randomIndex = random.nextInt(prizeObjects.length-1);            
                System.out.printf("[Prize %s] %s\n", i+1, prizeObjects[randomIndex].getPrizeName());
                totalPrice += prizeObjects[randomIndex].getPrizePrice();
            }
            System.out.println("What is the total cost of the following items?");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();

            if ((totalPrice >= input-1300) && (totalPrice <= input+1300)) {
                System.out.println("You won! The actual price was: $" + totalPrice);
            } else {
                System.out.println("You lost. The actual price was: $" + totalPrice);
            }

            System.out.println("Would you like to play again? (yes or no)");
            scanner.nextLine();
            quit = scanner.nextLine();
            if (quit.equalsIgnoreCase("no")) {
                System.exit(1);
            }
    }


    }



    // Modifyed code provided by JJ Shepard from CSE 146 Lab01/WorldHelper
    public static String[] readFile(String fileName)
	{
		try
		{
			//Creates a scanner for the file and then first counts each word
			Scanner fileScanner = new Scanner(new File(fileName));
			int lineCount = 0;
            String nextLine;
			while(fileScanner.hasNextLine())
			{
                    nextLine = fileScanner.nextLine();
                    lineCount++;
			}
			if(lineCount <= 0)
				return null;
			//Creates the return array, resets the file scanner, and populates the array
			String[] retArr = new String[lineCount];
			fileScanner = new Scanner(new File(fileName));
			for(int i=0;i<retArr.length;i++)
			{
				if(!fileScanner.hasNextLine()) {
					break;
                }
                nextLine = fileScanner.nextLine();
				retArr[i] = nextLine;

			}
			return retArr;
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}

    // method to help identify improperly formatted lines
    public static boolean checkFormat(String string) {
        return string.contains("\t");

    }



}




