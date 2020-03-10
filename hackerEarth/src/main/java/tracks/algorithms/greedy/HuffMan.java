package tracks.algorithms.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffMan {

    private static int decode(HuffManNode root, int index, StringBuilder sb){
        if (root == null)
            return index;
        if (root.left == null && root.right == null){
            System.out.print(root.ch);
            return index;
        }
        index++;
        if (sb.charAt(index) == '0')
            index = decode(root.left, index, sb);
        else
            index = decode(root.right, index, sb);
        return index;
    }

    private static void encode(HuffManNode root, String str, Map<Character, String> huffManCode){
        if (root == null)
            return;
        //if leaf node
        if (root.left == null && root.right == null)
            huffManCode.put(root.ch, str);
        encode(root.left, str + "0", huffManCode);
        encode(root.right, str + "1", huffManCode);
    }

    private static void buildHuffManTree(String text){
        Map<Character, Integer> chatToFreq = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char key = text.charAt(i);
            if (chatToFreq.get(key) != null)
                chatToFreq.put(key, chatToFreq.get(key) + 1);
            else
                chatToFreq.put(key, 1);
        }

        PriorityQueue<HuffManNode> priorityQueue = new PriorityQueue<>((a,b) -> a.freq - b.freq);
        for(Map.Entry<Character, Integer> entry : chatToFreq.entrySet()){
            priorityQueue.add(new HuffManNode(entry.getKey(), entry.getValue()));
        }

        while (priorityQueue.size() !=1){
            HuffManNode right = priorityQueue.poll();
            HuffManNode left = priorityQueue.poll();
            HuffManNode newNode = new HuffManNode('\0', left.freq + right.freq, left, right);
            priorityQueue.add(newNode);
        }

        HuffManNode root = priorityQueue.peek();
        Map<Character, String> huffManCode = new HashMap<>();
        encode(root, "", huffManCode);

        System.out.println("HuffManCodes are : ");
        for (Map.Entry<Character, String> entry : huffManCode.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("Original String was : " + text);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            stringBuilder.append(huffManCode.get(text.charAt(i)));
        }
        System.out.println("Encoded string is : " + stringBuilder);

        int index = -1;
        System.out.print("Decoded string is : ");
        while (index < stringBuilder.length() - 2)
            index = decode(root, index, stringBuilder);

    }

    public static void main(String[] args) {
        String text = "HuffManEncoding is a data Compression Algorithm";
        buildHuffManTree(text);
    }
}

class HuffManNode {
    char ch;
    int freq;
    HuffManNode right = null;
    HuffManNode left = null;

    public HuffManNode(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    public HuffManNode(char ch, int freq, HuffManNode right, HuffManNode left) {
        this.ch = ch;
        this.freq = freq;
        this.right = right;
        this.left = left;
    }
}
