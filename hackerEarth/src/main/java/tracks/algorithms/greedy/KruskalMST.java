package tracks.algorithms.greedy;

import java.util.*;

public class KruskalMST {


    static Map<Integer, Integer> vertexToParent = new HashMap<>();
    static Map<Integer, Integer> size = new HashMap<>();

    private static void union(int a, int b){
        int parentA = findParent(a);
        int parentB = findParent(b);
        if (parentA != parentB){
            if (size.get(parentA) < size.get(parentB)){
                vertexToParent.put(parentB, parentA);
                size.put(parentA, size.get(parentB) + size.get(parentA));
            } else {
                vertexToParent.put(parentA, parentB);
                size.put(parentB, size.get(parentB) + size.get(parentA));
            }
        }
    }

    private static int findParent(int src) {
        if (vertexToParent.get(src) == src)
            return src;
        else return findParent(vertexToParent.get(src));
    }

    private static List<KruskalMSTEdge> kruskalAlgo(List<KruskalMSTEdge> edges, int numVertices) {
        for (int i = 0; i < numVertices; i++) {
            vertexToParent.put(i, i);
            size.put(i, 1);
        }

        List<KruskalMSTEdge> resultEdges = new ArrayList<>();
        for (KruskalMSTEdge kruskalMSTEdge : edges){
            int parentSource = findParent(kruskalMSTEdge.src);
            int parentDest = findParent(kruskalMSTEdge.dest);
            if (parentSource != parentDest){
                union(kruskalMSTEdge.src, kruskalMSTEdge.dest);
                resultEdges.add(kruskalMSTEdge);
            }
        }
        return resultEdges;
    }

    public static void main(String[] args) {
        List<KruskalMSTEdge> edges = Arrays.asList(
                new KruskalMSTEdge(0, 1, 7), new KruskalMSTEdge(1, 2, 8),
                new KruskalMSTEdge(0, 3, 5), new KruskalMSTEdge(1, 3, 9),
                new KruskalMSTEdge(1, 4, 7), new KruskalMSTEdge(2, 4, 5),
                new KruskalMSTEdge(3, 4, 15), new KruskalMSTEdge(3, 5, 6),
                new KruskalMSTEdge(4, 5, 8), new KruskalMSTEdge(4, 6, 9),
                new KruskalMSTEdge(5, 6, 11)
        );

        Collections.sort(edges, (a,b) -> a.weight - b.weight);
        final int N = 7;
        List<KruskalMSTEdge> allEdges = kruskalAlgo(edges, N);
        for (KruskalMSTEdge edge : allEdges)
            System.out.println(edge);
    }
}

class KruskalMSTEdge {
    int src, dest, weight;

    public KruskalMSTEdge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public String toString() {
        return "(" + src + ", " + dest + ", " + weight + ")";
    }
}
