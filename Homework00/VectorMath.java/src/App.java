
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the vector calculator by Oliver Benjamin\nplease enter the size of your vector");
    }

    public static int[] add(int A1[], int A2[]) {
        int A3[] = null;
        for (int x = 0; x < A1.length; x++) {
            A3[x] = A1[x] + A2[x];
        }
        return A3;
    }

    public static int[] subtract(int A1[], int A2[]) {
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
}
