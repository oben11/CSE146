import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the vector calculator by Oliver Benjamin\nplease enter the size of your first vector: ");
        int size = scanner.nextInt();
        int A1[] = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.printf("\nenter an integer for slot %s of Vector 1: ", i+1);
            A1[i] = scanner.nextInt();
            System.out.print("Vector 1: ");
            printVector(A1);
        }

        System.out.println("\nplease enter the size of your second vector: ");
        size = scanner.nextInt();
        int A2[] = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.printf("\nenter an integer for slot %s of Vector 2: ", i+1);
            A2[i] = scanner.nextInt();
            System.out.print("Vector 2: ");
            printVector(A2);
        }

        while(!"q".equals(scanner.nextLine())) {
            System.out.println("Vectors stored:");
            
            System.out.print("Vector1: ");
            printVector(A1);

            System.out.print("Vector2: ");
            printVector(A2);
            
            System.out.println("Options:\n1: add\n2: subtract\n3: take magnitude of a vector");
            String response = scanner.nextLine();
            switch (response) {
                case "add" : 
                add(A1, A2);

                case "subtract" :
                subtract(A1, A2);

                case "magnitude" :
                System.out.println("Do you need the magnitude of vector 1 or vector 2?\n type 1 for vector 1\ntype 2 for vector 2\n");
                response = scanner.nextLine();
                if (response.equals("1")) {
                    magnitude(A1);

                } else if (response.equals("2")) {
                    magnitude(A1);
                } else {
                    System.out.println("please input 1 or 2 invalid response");
                    return;
                }



            }

        }
    }

    public static void printVector(int[] A1) {
        for (Object obj : A1) {
            System.out.printf("[%s]", obj);
        }
        System.out.print("\n");
    }

    public static boolean checkVectors(int []A1, int [] A2) {
        return A1.length == A2.length;
    }

    public static int[] add(int A1[], int A2[]) {
        if(!checkVectors(A1, A2)) {
            System.out.println("Sorry, you may not add vectors who's size do not match!");
            return null;
        }
        int A3[] = null;
        for (int x = 0; x < A1.length; x++) {
            A3[x] = A1[x] + A2[x];
        }
        return A3;
    }

    public static int[] subtract(int A1[], int A2[]) {
        if(!checkVectors(A1, A2)) {
            System.out.println("Sorry, you may not add vectors who's size do not match!");
            return null;
        }
        int A3[] = null;
        for (int x = 0; x < A1.length; x++) {
            A3[x] = A1[x] - A2[x];
        }
        return A3;
    }

    public static double magnitude(int A1[]) {
        double temp = 0;
        for (int x = 0; x < A1.length; x++) {
            temp += Math.sqrt(A1[x]*A1[x]);
            
        }
        return temp;
    }

    private static void Switch(String response) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
