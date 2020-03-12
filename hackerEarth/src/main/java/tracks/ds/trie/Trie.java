package tracks.ds.trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    public Map<Character, Trie> children = new HashMap<>();
    public boolean isLeaf = false;

    public void add(String s){
        Trie curr = this;
        for (int i = 0; i < s.length(); i++) {
            if (curr.children.get(s.charAt(i)) == null)
                curr.children.put((s.charAt(i)), new Trie());
            curr = curr.children.get(s.charAt(i));
        }
        curr.isLeaf = true;
    }

    public boolean search(String s){
        Trie curr = this;
        for (int i = 0; i < s.length(); i++) {
            if (curr.children.get(s.charAt(i)) != null){
                curr = curr.children.get(s.charAt(i));
            }else
                return false;
        }
        return curr.isLeaf;
    }

    public String startsWith(String s){
        Trie curr = this;
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            if (curr.children.get(s.charAt(i))!=null){
                curr = curr.children.get(s.charAt(i));
                res+=s.charAt(i);
            }else {
                break;
            }
        }
        if (res.length() > 0 && curr.isLeaf)
            return res;
        return s;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("banana");
        trie.add("ana");
        trie.add("mad");
        trie.add("madam");

        System.out.println(trie.search("ana"));
        System.out.println(trie.search("mad"));
        System.out.println(trie.search("and"));
        System.out.println(trie.search("dam"));
        System.out.println(trie.search("ban"));
    }
}
