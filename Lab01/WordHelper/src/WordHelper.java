// Oliver Benjamin 
// CSE 146
// Lab00
public class WordHelper {
    // Sorting Methods
        public static String[] sortByVowels(String[] list) {
            // every function does this but it's very inneficient, If I had access to the driver file I would change it to only populate the word objects once
            Word[] words = new Word[list.length];
            for (int i = 0; i < list.length; i++) {
                words[i] = new Word(list[i]);
            }
    
            // basic bubblesort implementation just using do instead of while at the top like my last lab for a cleaner look.
            boolean swapped;
            do {
                swapped = false;
                for (int i = 0; i < words.length - 1; i++) {
                    if (words[i].getNumVowels() > words[i + 1].getNumVowels()) {
                        Word temp = words[i];
                        words[i] = words[i + 1];
                        words[i + 1] = temp;
                        swapped = true;
                    }
                }
            } while (swapped);
    
            String[] sortedList = new String[words.length];
            for (int i = 0; i < words.length; i++) {
                sortedList[i] = words[i].getWord();
            }
    
            return sortedList;
        }
    

    // Exact same implentation except sorts by word objects Cons #.
    public static String[] sortByConsonants(String[] list) {
        Word[] words = new Word[list.length];
        for (int i = 0; i < list.length; i++) {
            words[i] = new Word(list[i]);
        }

        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < words.length - 1; i++) {
                if (words[i].getNumConsonants() > words[i + 1].getNumConsonants()) {
                    Word temp = words[i];
                    words[i] = words[i + 1];
                    words[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);

        String[] sortedList = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            sortedList[i] = words[i].getWord();
        }

        return sortedList;

    }
    // Exact same implentation except sorts by word objects length
    public static String[] sortByLength(String[] list) {
        Word[] words = new Word[list.length];
        for (int i = 0; i < list.length; i++) {
            words[i] = new Word(list[i]);
        }

        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < words.length - 1; i++) {
                if (words[i].getLength() > words[i + 1].getLength()) {
                    Word temp = words[i];
                    words[i] = words[i + 1];
                    words[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);

        String[] sortedList = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            sortedList[i] = words[i].getWord();
        }

        return sortedList;
    }
}
