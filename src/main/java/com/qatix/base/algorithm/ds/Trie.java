package com.qatix.base.algorithm.ds;

public class Trie {
    static class Node {
        Node[] next;
        boolean isEnd;

        Node() {
            next = new Node[26];
            isEnd = false;
        }
    }

    Node root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new Node();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            int t = word.charAt(i) - 'a';
            if (cur.next[t] == null) {
                cur.next[t] = new Node();
            }
            cur = cur.next[t];
        }
        cur.isEnd = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            int t = word.charAt(i) - 'a';
            if (cur.next[t] == null) {
                return false;
            }
            cur = cur.next[t];
        }
        return cur.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            int t = prefix.charAt(i) - 'a';
            if (cur.next[t] == null) {
                return false;
            }
            cur = cur.next[t];
        }
        return true;
    }
}

