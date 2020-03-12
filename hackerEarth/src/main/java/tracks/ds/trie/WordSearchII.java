package tracks.ds.trie;

import java.util.*;

public class WordSearchII {
    class Trie{
        boolean isLeaf = false;
        Map<Character, Trie> children = new HashMap<>();

        public void add(String s, Trie t){
            Trie curr = t;
            for (int i = 0; i < s.length(); i++) {
                if (curr.children.get(s.charAt(i)) == null){
                    curr.children.put(s.charAt(i), new Trie());
                }
                curr = curr.children.get(s.charAt(i));
            }
            curr.isLeaf = true;
        }

        public boolean search(String s){
            Trie curr = this;
            for (int i = 0; i < s.length(); i++) {
                if (curr.children.get(s.charAt(i))!=null)
                    curr = curr.children.get(s.charAt(i));
                else
                    return false;
            }
            return curr != null;
        }
    }

    int row[] = {-1, 1, 0, 0};
    int col[] = {0, 0, -1, 1};

    private static int M, N;

    private boolean isSafe(int currRow, int currCol, boolean[][] processed, char ch, char[][] board){
        return (currRow >=0 && currRow < M) && (currCol>=0 && currCol<N) && (!processed[currRow][currCol])
                && (board[currRow][currCol] == ch);
    }

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (int i = 0; i < words.length; i++) {
            trie.add(words[i], trie);
        }

        M = board.length;
        N = board[0].length;
        boolean processed[][] = new boolean[M][N];
        Set<String> wordsCreated = new HashSet<>();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                Character ch = board[i][j];
                if (trie.children.containsKey(ch)){
                    String s = Character.toString(ch);
                    searchboggle(board, wordsCreated, processed, i, j, s, trie.children.get(ch));
                }
            }
        }

        List<String> results = new ArrayList<>();
        for(String word : words){
            if (wordsCreated.contains(word))
                results.add(word);
        }
        return results;
    }

    private void searchboggle(char[][] board, Set<String> wordsCreated, boolean[][] processed, int i, int j, String word, Trie trie) {
        if (trie.isLeaf)
            wordsCreated.add(word);
        processed[i][j] = true;
        for(Map.Entry<Character, Trie> it : trie.children.entrySet()){
            for (int k = 0; k < 4; k++) {
                if (isSafe(i + row[k], j + col[k], processed, it.getKey(), board)){
                    searchboggle(board, wordsCreated, processed, i + row[k], j + col[k], word + it.getKey(), it.getValue());
                }
            }
        }
        processed[i][j] = false;
    }

    public static void main(String[] args) {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};
        new WordSearchII().findWords(board, words);
    }
}
