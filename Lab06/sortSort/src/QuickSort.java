// Oliver Benjamin
// CSE146
// Lab06

/* 
 basic quicksort implementation from JJShedpard @UofSC
 modifyed to sort by countOccurances(string) instead of int array
 */
public class QuickSort {
    // constructor
    public static void quickSort(String[] a) {
        quickSort(a, 0, a.length - 1);
    }

    public static void quickSort(String[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = partition(a, start, end);
        quickSort(a, start, pivot - 1);
        quickSort(a, pivot + 1, end);
    }

    public static int partition(String[] a, int start, int end) {
        String pivot = a[end];
        int i = start;
        for (int j = start; j <= end; j++) {
            if (countOccurrences(a[j], "sort") < countOccurrences(pivot, "sort")) {
                String temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
            }
        }
        String temp = a[i];
        a[i] = a[end];
        a[end] = temp;
        return i;
    }
    
    // counts occurrences of a target word in string
    public static int countOccurrences(String string, String word) {
        // if the string is empty don't consider it at all
        if (string == null || word == null || word.isEmpty()) {
            return 0;
        }

        int count = 0;
        int index = 0;

        // while word word occurs in string, casts word to lower case because index of doesn't ignore case.
        while ((index = string.toLowerCase().indexOf(word, index)) != -1) { 
            count++;
            index += word.length(); // move past the found occurrence of word sort
        }

        return count;
    }


}