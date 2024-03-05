import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// Program to calculate the longest compound and the second longest compound word in a file

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    TrieNode() {
        children = new TrieNode[26]; // Assuming only lowercase English letters
        isEndOfWord = false;
    }
}

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // Start timing
        long startTime = System.currentTimeMillis();

        // Importing file from which compound word is to be calculated
        File file = new File("C:\\Users\\Tushar Kansal\\Downloads\\Input_02.txt");

        // Creating a Trie
        Trie trie = new Trie();
        LinkedList<Pair<String>> queue = new LinkedList<Pair<String>>();

        // To calculate the total number of compound words
        HashSet<String> compoundWords = new HashSet<String>();

        // scan the file
        @SuppressWarnings("resource")
        Scanner s = new Scanner(file);

        String word;
        List<Integer> suffIndices;	// indices of suffixes of a word

        // Read words from the file
        // Fill the queue with words which have suffixes, which can be compound words
        // Insert each word in trie
        while (s.hasNext()) {
            word = s.next();
            suffIndices = trie.getSuffixesStartIndices(word);

            for (int i : suffIndices) {
                if (i >= word.length())        // Checking Index out of bound
                    break;
                queue.add(new Pair<String>(word, word.substring(i)));
            }
            trie.insert(word);
        }

        Pair<String> p;
        int maxLength = 0;
        String longest = "";
        String sec_longest = "";

        while (!queue.isEmpty()) {
            p = queue.removeFirst();
            word = p.second();

            suffIndices = trie.getSuffixesStartIndices(word);

            // if no suffixes found, which means no prefixes found, discard the pair and check the next pair
            if (suffIndices.isEmpty()) {
                continue;
            }

            for (int i : suffIndices) {
                if (i > word.length()) {
                    break;
                }

                if (i == word.length()) {
                    // no suffix, means it is a compound word
                    // check if the compound word is the longest
                    // if it is update both longest and second longest
                    if (p.first().length() > maxLength) {
                        sec_longest = longest;
                        maxLength = p.first().length();
                        longest = p.first();
                    }

                    compoundWords.add(p.first());

                } else {
                    queue.add(new Pair<String>(p.first(), word.substring(i)));
                }
            }
        }

        // End timing
        long endTime = System.currentTimeMillis();

        //Process time
        long duration = (endTime - startTime) ;

        //Displaying the results
        System.out.println("Longest Compound Word is: " + longest);
        System.out.println("Second Longest Compound Word is: " + sec_longest);
        System.out.println("Time taken to process the file is: " + duration + " milliseconds");
    }
}