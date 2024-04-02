package com.dsa.solutions.trie;

class TrieNode {
    // Each TrieNode contains an array of children TrieNodes to represent each letter of the alphabet
    TrieNode[] children = new TrieNode[26];
    // Indicates if a word ends at this node
    boolean isEndOfWord;
}

class WordDictionary2 {
    private TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary2() {
        root = new TrieNode();
    }

    // Adds a word into the data structure
    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                // If there is no TrieNode for this letter, create a new TrieNode
                node.children[index] = new TrieNode();
            }
            // Move to the next node
            node = node.children[index];
        }
        // Mark this node as the end of a word
        node.isEndOfWord = true;
    }

    // Searches for a word in the data structure and can handle '.' as a wildcard character
    public boolean search(String word) {
        return searchInNode(word, root);
    }

    private boolean searchInNode(String word, TrieNode node) {
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (c == '.') {
                // If it's a wildcard, we need to check all possible paths
                for (TrieNode child : node.children) {
                    if (child != null && searchInNode(word.substring(i + 1), child)) {
                        return true;
                    }
                }
                return false; // If no paths find a match, return false
            } else {
                int index = c - 'a';
                // If the specific child TrieNode does not exist, the word does not exist
                if (node.children[index] == null) {
                    return false;
                }
                // Move to the next node
                node = node.children[index];
            }
        }
        // After processing all characters, check if it is the end of a word
        return node.isEndOfWord;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
