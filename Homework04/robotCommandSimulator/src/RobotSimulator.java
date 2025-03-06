// Oliver Benjamin
// CSE 146
// Homework 04


import java.io.*;
import java.util.*;

public class RobotSimulator {
    static int RbtX = 0;
    static int RbtY = 0;
    static RbtCmds<String> Cmds = new RbtCmds<>();
    
    
        // Pretty Move function
        public static void Move(String dir) {
            switch(dir) {
            case "Move Up" -> RbtY += 1;
            case "Move Right" -> RbtX += 1;
            case "Move Down" -> RbtY -= 1;
            case "Move Left" -> RbtX -=1;
            }
        }
    
        // Given RbtX and RbtY displays the current board, returns false if crashed returns true if alive.
        //matricies would make this so much easier but not allowed :(
        public static boolean Display(int RbtX, int RbtY, String Board) {
            int x = 0, y = 0;
            boolean alive = true;
            for (int i = 0; i < Board.length(); i++) {
                if (Board.charAt(i) == '\n') { // manage x and y pos
                    x=0;
                    y--;
                    System.out.print(Board.charAt(i)); // print new line
                    continue; // continues to avoid screwing up x count
                }
                if ((x == RbtX) && (y == RbtY)) { // Robot pos
                    if (Board.charAt(i) == 'X') {
                        System.out.print('*'); // died
                        alive = false;
                    } else {
                        System.out.print('O'); // new position
                    }
                } else {
                    System.out.print(Board.charAt(i)); // prints if no conditions are met like a new player position or crash
                }
                x++;
            }
            System.out.println();
            return alive;
        }
    
        // Populates custom queue class from a given robot command file
        public static void PopCmds(String fileName) {
            System.out.println("Loading commands from " + fileName + "...");
            try {
                try (Scanner fileScanner = new Scanner(new File(fileName))) {
                    while(fileScanner.hasNextLine()) 
                    {
                        // Could be made more efficient by executing directly from file
                        //Move(fileScanner.nextLine().split(" ")[1]);
                        Cmds.enqueue(fileScanner.nextLine());
                    }
                }
            } catch (Exception e) {
                System.out.println("I couldn't find: " + fileName + e);
            }
        }
    

    public static String PopBoard(String fileName) {
            System.out.println("Loading board from " + fileName + "...");
        
            StringBuilder temp = new StringBuilder();
            try (Scanner fileScanner = new Scanner(new File(fileName))) {
                while (fileScanner.hasNextLine()) {
                    if (temp.length() > 0) temp.append("\n");
                    temp.append(fileScanner.nextLine());
                }
            } catch (Exception e) {
                System.out.println("I couldn't find: " + fileName);
            }
            return temp.toString();
    }

    // play the command queue
    public static void Play(String Board) {
        while(Cmds.size()!=0) {
            System.out.printf("Command: %s from [%s, %s]\n", Cmds.peek(), RbtX, RbtY);
            Move(Cmds.dequeue());
            if (Display(RbtX, RbtY, Board) == false) {
                System.out.println("CRASH!!");
                return;
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the Robot Simulator");
            System.out.println("Enter file for the Board");
            String Board = PopBoard(scanner.nextLine());
            System.out.println("Enter file for the Robot Commands");
            PopCmds(scanner.nextLine());
            Play(Board);
        }
    }

}
