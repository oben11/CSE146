// Oliver Benjamin
// CSE 146


import java.util.Scanner;

public class App {
    public static final int ARRAY_SIZE = 10;
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter %s numbers and I'll sort them", ARRAY_SIZE);
        int[] list = new int[ARRAY_SIZE];

        for (int i = 0; i < list.length; i++) {
            System.out.printf("\nEnter value %s -> ", i);
            list[i] = scanner.nextInt();
        }
        // Bubble Sort
        boolean hasSwapped = true;
        while (hasSwapped) {
            hasSwapped = false;
            for (int i = 0; i < list.length-1; i++) {
                if (list[i]>list[i+1]) {
                    int temp = list[i];
                    list[i] = list[i+1];
                    list[i+1] = temp;
                    hasSwapped = true;
                }
            }
        }


        //print array
        for (int i=0; i<list.length;i++) {
            System.out.print(list[i] + " ");
        }
    }
}
