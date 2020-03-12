package tracks.algorithms.greedy;

import java.util.*;

public class GraphColoring {

    private static String[] color = {
            "", "BLUE", "GREEN", "RED", "YELLOW", "ORANGE", "PINK",
            "BLACK", "BROWN", "WHITE", "PURPLE", "VOILET"
    };

    private static void colorGraph(Graph graph, int n) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int u = 0; u < n; u++) {
            Set<Integer> assigned = new HashSet<>();
            for(int i : graph.adjList.get(u)) {
                if (result.containsKey(i))
                    assigned.add(result.get(i));
            }

            int color = 1;
            for (Integer c : assigned){
                if (color !=c)
                    break;
                color++;
            }
            result.put(u, color);
        }

        for (int v = 0; v < n; v++) {
            System.out.println("Color assigned to "+ v +" is "+ color[result.get(v)]);
        }
    }

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1), new Edge(0, 4),
                new Edge(0, 5), new Edge(4, 5),
                new Edge(1, 4), new Edge(1, 3),
                new Edge(2, 3), new Edge(2, 4)
        );

        // Set number of vertices in the graph
        final int N = 6;

        // create a graph from edges
        Graph graph = new Graph(edges, N);

        // color graph using greedy algorithm
        colorGraph(graph, N);
    }
}

class Graph {

    List<List<Integer>> adjList;

    public Graph(List<Edge> edges, int numEdges) {
        adjList = new ArrayList<>(numEdges);
        for (int i = 0; i < numEdges; i++) {
            adjList.add(i, new ArrayList<>());
        }

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            adjList.get(edge.src).add(edge.dest);
            adjList.get(edge.dest).add(edge.src);
        }
    }
}

class Edge {
    int src;
    int dest;

    public Edge(int src, int dest) {
        this.src = src;
        this.dest = dest;
    }
}