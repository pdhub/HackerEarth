package tracks.algorithms.greedy;

import java.util.*;

public class Dijikstra {


    private static void shortestPath(DijikstraGraph graph, int source, int N) {
        PriorityQueue<DijikstraNode> minHeap = new PriorityQueue<>((a,b)->a.weight - b.weight);
        minHeap.add(new DijikstraNode(source, 0));

        List<Integer> dist = new ArrayList<>(Collections.nCopies(N, Integer.MAX_VALUE));
        dist.set(source, 0);

        boolean[] done = new boolean[N];
        done[0] = true;

        int[] prev = new int[N];
        prev[0] = -1;

        while (!minHeap.isEmpty()){
            DijikstraNode node = minHeap.poll();
            int u = node.vertex;
            for (DijikstraEdge edge : graph.adjList.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;
                if (!done[v] && (dist.get(u) + weight) < dist.get(v)){
                    dist.set(v, dist.get(u) + weight);
                    prev[v] = u;
                    minHeap.add(new DijikstraNode(v, dist.get(v)));
                }
            }
            done[u] = true;
        }

        for (int i = 1; i < N; ++i)
        {
            System.out.print("Path from vertex 0 to vertex " + i +
                    " has minimum cost of " + dist.get(i) +
                    " and the route is [ ");
            printRoute(prev, i);
            System.out.print("]");
            System.out.println();
        }
    }

    private static void printRoute(int[] prev, int i) {
        if (i < 0)
            return;
        printRoute(prev, prev[i]);
        System.out.print(prev[i] + " ");
    }

    public static void main(String[] args) {
        List<DijikstraEdge> edges = Arrays.asList(
                new DijikstraEdge(0, 1, 10), new DijikstraEdge(0, 4, 3),
                new DijikstraEdge(1, 2, 2), new DijikstraEdge(1, 4, 4),
                new DijikstraEdge(2, 3, 9), new DijikstraEdge(3, 2, 7),
                new DijikstraEdge(4, 1, 1), new DijikstraEdge(4, 2, 8),
                new DijikstraEdge(4, 3, 2)
        );
        final int N = 5;
        DijikstraGraph graph = new DijikstraGraph(edges, N);
        shortestPath(graph, 0, N);
    }
}

class DijikstraNode{
    int vertex, weight;

    public DijikstraNode(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

class DijikstraEdge{
    int src, dest, weight;

    public DijikstraEdge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class DijikstraGraph{
    List<List<DijikstraEdge>> adjList = new ArrayList<>();

    public DijikstraGraph(List<DijikstraEdge> edges, int numVertices) {
        for (int i = 0; i < numVertices; i++) {
            adjList.add(i, new ArrayList<>());
        }

        for (DijikstraEdge edge : edges){
            adjList.get(edge.src).add(edge);
        }
    }
}
