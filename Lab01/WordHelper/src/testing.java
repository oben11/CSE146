
import java.io.*;
import java.util.*;

public class testing {


    public static final String FILE_NAME = "./randomWords.txt"; 
    
    public static void main(String[] args) {
        String[] words = readFile(FILE_NAME);
        System.out.println("The original word list");
        printStrArr(words);
    }
    
    public static String[] readFile(String fileName) {
        try {
            Scanner fileScanner = new Scanner(new File(fileName));
            int wordCount = 0;
            while (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
                wordCount++;
            }
            if (wordCount <= 0)
                return null;
            
            String[] retArr = new String[wordCount];
            fileScanner = new Scanner(new File(fileName));
            for (int i = 0; i < retArr.length; i++) {
                if (!fileScanner.hasNextLine())
                    break;
                retArr[i] = fileScanner.nextLine();
            }
            return retArr;
        } catch (IOException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static void printStrArr(String[] words) {
        if (words != null) {
            for (String s : words)
                System.out.println(s);
        }
    }
}




