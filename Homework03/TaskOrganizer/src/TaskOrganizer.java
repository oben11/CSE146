// Oliver Benjamin
// CSE146
// Homework03

import java.io.*;
import java.util.Scanner;


public class TaskOrganizer {    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GenLL<Task>[] organizedTasks = new GenLL[5];

        for (int i = 0; i < organizedTasks.length; i++) {
            organizedTasks[i] = new GenLL<>();
        }

        while (true) {
            System.out.println("\nWelcome to the Task Organizer!");
            System.out.println("Enter 1. To Addd a Task");
            System.out.println("Enter  2. To Remove a Task");
            System.out.println("Enter 3. To Print tasks To Console");
            System.out.println("Enter 4. To read from a Task File");
            System.out.println("Enter 5. To Write to a Task File");
            System.out.println("Enter 9. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTask(scanner, organizedTasks);
                    break;
                case 2:
                    removeTask(scanner, organizedTasks);
                    break;
                case 3:
                    printTasks(organizedTasks);
                    break;
                case 4:
                    readTasksFromFile(scanner, organizedTasks);
                    break;
                case 5:
                    writeTasksToFile(scanner, organizedTasks);
                    break;
                case 9:
                    System.out.println("Exiting");
                    break;
                default:
                System.out.println("Incorrect");
            }
        }
    }

    public static void addTask(Scanner scanner, GenLL<Task>[] organizedTasks) {
        System.out.println("Enter the task's priority");
        int priority = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter the task's action");
        String action = scanner.nextLine();

        if (priority >= 0 && priority <= 4) {
            Task newTask = new Task(action, priority);
            if (!organizedTasks[priority].contains(newTask)) {
                organizedTasks[priority].add(newTask);
            } else {
                System.out.println("Duplicate task. Task not adde");
            }
        } else {
            System.out.println("Invalid   priority.");
        }
    }

    public static void removeTask(Scanner scanner, GenLL<Task>[] organizedTasks) {
        System.out.println("Enter the task's priority");
        int priority = scanner.nextInt();
        scanner.nextLine();


        System.out.println("Enter the task's action");
        String action = scanner.nextLine();

        if (priority >= 0 && priority <= 4) {
            Task taskToRemove = new Task(action, priority);
            organizedTasks[priority].remove(taskToRemove);
        } else {
            System.out.println("Invalid priority.");
        }
    }

    public static void printTasks(GenLL<Task>[] organizedTasks) {
        for (GenLL<Task> organizedTask : organizedTasks) {
            organizedTask.printList();
        }
    }

    public static void readTasksFromFile(Scanner scanner, GenLL<Task>[] organizedTasks) {
        System.out.println("Enter the file name");
        String fileName = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < organizedTasks.length; i++) {
                organizedTasks[i] = new GenLL<>();
            }

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 2) {
                    try {
                        int priority = Integer.parseInt(parts[0]);
                        String action = parts[1];
                        if (priority >= 0 && priority <= 4) {
                            organizedTasks[priority].add(new Task(action, priority));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid line format: " + line);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeTasksToFile(Scanner scanner, GenLL<Task>[] organizedTasks) {
        System.out.println("Enter the file name");
        String fileName = scanner.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < organizedTasks.length; i++) {
                    GenLL.Node<Task> current = organizedTasks[i].getHead();                
            while (current != null) {
                    bw.write(current.data.priority + "\t" + current.data.action + "\n");
                    current = current.next;
            }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}