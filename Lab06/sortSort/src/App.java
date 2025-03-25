// Oliver Benjamin
// CSE146
// Lab06

import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);
            
    public static void main(String[] args) throws Exception {
            System.out.println("\nWelcome to the sort sorter, this program allows you to sort strings the number of \"sorts\" that they contain\ntype \"sort\" to start sorting your sorts or...\ntype \"quit\" to quit");

            boolean quit = false;
            // main loop
            while(quit==false) {
                System.out.println("\nWow that was fun want to sort again?\ntype \"sort\" to start sorting your sorts or...\ntype \"quit\" to quit");
                String input = scanner.nextLine();
            
            // option menu
            switch (input) {
                case "sort" -> CollectandPrintStrings();
                case "quit" -> quit=true;
                default -> throw new AssertionError();
            }
        }
    }

    public static void CollectandPrintStrings() {
        System.out.println("\nAlright! type as many sentences as you like and I'll sort them by the number of \"sorts\" they contain\ntype \"quit\" when done");

        /* 
        Gimmicky way of collection multiple strings without having to 
        assign them to a LinkedList, ArrayList or custom implemented list
        maybe it's more efficient? it's less code at the very least.
        */
        String combinedStrings = "";
        // string collection loop
        boolean quit = false;
            while(quit==false) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("quit")) {
                    quit = true;
                } else {
                    combinedStrings += input + ",";
                }
        }
        String[] Strings = combinedStrings.split(",");

        // quicksort and print sorted list
        QuickSort.quickSort(Strings);
        System.out.println("\n↑ Least \"sorts\"");
        for (String s : Strings) {
            System.out.println("| "+s);
        }
        System.out.println("↓ Most \"sorts\"\n");
    }


}
