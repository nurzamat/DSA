package com.dsa.solutions.trie;

import java.util.HashMap;
import java.util.Map;

//211. Design Add and Search Words Data Structure
class WordDictionary {

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
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
        for (int i=0; i<word.length(); i++) {
            Character ch = word.charAt(i);
            if(ch == '.'){
                ch = word.charAt(i+1);
                TrieNode node = null;
                for (Map.Entry<Character, TrieNode> entry:curent.children.entrySet()) {
                    node = entry.getValue().children.get(ch);
                    if(node != null)
                        break;
                }
                if(node == null){
                    return false;
                }
                i = i + 1;
                curent = node;
            }
            else{
                TrieNode node = curent.children.get(ch);
                if(node == null){
                    return false;
                }
                curent = node;
            }
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

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */