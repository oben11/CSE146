// Oliver Benjamin 
// CSE 146
// Lab00

public class Word {
    private String word;
    private int numVowels;
    private int length;
    private int numConsonants;

    // Constructor creates object from given string
    public Word(String word) {
        this.word = word;
        this.length = word.length();
        this.numVowels = countVowels(word);
        this.numConsonants = length - numVowels;
    }

    // Counts the vowels in each word much smaller implementation than .charAT() == "a" || etc etc.
    // the first way I implemented it was this geeksforgeeks method https://www.geeksforgeeks.org/java-program-to-count-number-of-vowels-in-a-string/ 
    private int countVowels(String word) {
        int count = 0;
        String vowels = "aeiou";
        for (int i = 0; i < word.length(); i++) {
            if (vowels.indexOf(Character.toLowerCase(word.charAt(i))) != -1) {
                count++;
            }
        }
        return count;
    }

    // Accsessors
    public String getWord() {
        return word;
    }

    public int getNumVowels() {
        return numVowels;
    }

    public int getLength() {
        return length;
    }

    public int getNumConsonants() {
        return numConsonants;
    }
}
