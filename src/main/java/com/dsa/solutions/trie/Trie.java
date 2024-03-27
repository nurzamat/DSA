package com.dsa.solutions.trie;

import java.util.HashMap;
import java.util.Map;

//208. Implement Trie (Prefix Tree)
class Trie {
    private TrieNode root;

    public Trie() {
      this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curent = root;
        for (Character ch:word.toCharArray()) {
            TrieNode node = curent.children.get(ch);
            if(node == null){
                node = new TrieNode();
                curent.children.put(ch, node);
            }
            curent = node;
        }
        curent.endOfString = true;
    }

    public boolean search(String word) {
        TrieNode curent = root;
        for (Character ch:word.toCharArray()) {
            TrieNode node = curent.children.get(ch);
            if(node == null){
                return false;
            }
            curent = node;
        }
        return curent.endOfString;
    }

    public boolean startsWith(String prefix) {
        TrieNode curent = root;
        for (Character ch:prefix.toCharArray()) {
            TrieNode node = curent.children.get(ch);
            if(node == null){
                return false;
            }
            curent = node;
        }
        return true;
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
