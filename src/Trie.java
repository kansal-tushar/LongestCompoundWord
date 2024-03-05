import java.util.ArrayList;
import java.util.List;

public class Trie {
    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    // Returns the start indices of suffixes of a word in the trie.
    public List<Integer> getSuffixesStartIndices(String word) {
        List<Integer> suffixIndices = new ArrayList<>();
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (current.children[index] == null) {
                return suffixIndices;
            }
            current = current.children[index];
            if (current.isEndOfWord) {
                suffixIndices.add(i + 1);
            }
        }
        return suffixIndices;
    }
}
