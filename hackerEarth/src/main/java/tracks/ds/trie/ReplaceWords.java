package tracks.ds.trie;

import java.util.Arrays;
import java.util.List;

public class ReplaceWords {
    public String replaceWords(List<String> dict, String sentence) {

        Trie trie = new Trie();
        for (String words : dict){
            trie.add(words);
        }

        String[] words = sentence.split(" ");
        String result = "";
        for (String word : words){
            String startsWith = trie.startsWith(word);
            result += startsWith + " ";
        }
        return result.trim();
    }

    public static void main(String[] args) {
        String[] dict = {"cat", "bat", "rat"};
        String sentence = "the cattle was rattled by the battery";
        System.out.println(new ReplaceWords().replaceWords(Arrays.asList(dict), sentence));
    }
}
