package com.dsa.solutions.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//211. Design Add and Search Words Data Structure
class WordDictionary {
    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode current = root;
        Character ch;
        for(int i=0; i<word.length(); i++){
            ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if(node == null){
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        current.endOfString = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        Character ch;
        TrieNode node = null;
        List<TrieNode> list = null;
        for(int i=0; i<word.length(); i++){
            ch = word.charAt(i);
            if(ch == '.'){
                if(list != null){
                    List<TrieNode> subList = new ArrayList();
                    for(TrieNode trNode:list){
                        for(Map.Entry<Character, TrieNode> entry: trNode.children.entrySet()){
                            subList.add(entry.getValue());
                        }
                    }
                    list = subList;
                }
                else{
                    list = new ArrayList();
                    for(Map.Entry<Character, TrieNode> entry: current.children.entrySet()){
                        list.add(entry.getValue());
                    }
                    current = null;
                }
            }
            else{
                if(list != null){
                    for(TrieNode trNode:list){
                        node = trNode.children.get(ch);
                        if(node != null){
                            break;
                        }
                    }
                    if(node == null)
                        return false;
                    current = node;
                    list = null;
                }
                else{
                    node = current.children.get(ch);
                    if(node == null){
                        return false;
                    }
                    current = node;
                    list = null;
                }
            }
        }
        if(current != null && !current.endOfString)
            return false;

        if(list != null){
            boolean hasEnd = false;
            for(TrieNode trNode:list){
                if(trNode.endOfString){
                    hasEnd = true;
                    break;
                }
            }
            return hasEnd;
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