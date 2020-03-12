package tracks.ds.trie;

import java.util.Map;

class MagicDictionary {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;
    }

    private TrieNode root;
    /** Initialize your data structure here. */
    public MagicDictionary() {
        root = new TrieNode();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String s : dict) {
            TrieNode cur = root;
            for (char ch : s.toCharArray()) {
                if (cur.children[ch - 'a'] == null) cur.children[ch - 'a'] = new TrieNode();
                cur = cur.children[ch - 'a'];
            }
            cur.isWord = true;
        }
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        return search(root, word.toCharArray(), 0, 0);
    }

    public boolean search(TrieNode curr, char[] word, int index, int count){
        if (curr == null || count > 1)
            return false;
        if (index == word.length && count == 1)
            return curr.isWord;
        for (int i = 0; i < 26; ++i) {
            if (search(curr.children[i], word, index + 1, (word[index] - 'a' != i) ? count + 1 : count)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello", "hallo", "leetcode"});
        System.out.println(magicDictionary.search("hello"));
        System.out.println(magicDictionary.search("hhllo"));
        System.out.println(magicDictionary.search("hell"));
        System.out.println(magicDictionary.search("leetcoded"));
    }

}
