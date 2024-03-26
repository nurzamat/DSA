package com.dsa.solutions.trie;

import java.util.HashMap;
import java.util.Map;

//208. Implement Trie (Prefix Tree)
class Trie {


    public Trie() {

    }

    public void insert(String word) {

    }

    public boolean search(String word) {

    }

    public boolean startsWith(String prefix) {

    }


    class TrieNode{
        Map<Character, TrieNode> children;
        boolean endOfString;

        public TrieNode(){
            children = new HashMap();
            endOfString = false;
        }
    }
}
