package topologialsort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TopologicalSort {

    public static List<Integer> sort(int vertices, int [][] edges) {
        List<Integer> sortedOrder = new ArrayList<>();
        if (vertices <= 0) {
            return sortedOrder;
        }

        // count number of incoming edges for each vertex
        HashMap<Integer, Integer> inDegree = new HashMap<>();
        // Map with adjacency lists.
        HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();

        // Step 1: Initialize the graph
        for (int i = 0; i < vertices; i++) {
            graph.put(i, new ArrayList<Integer>());
            inDegree.put(i, 0);
        }

        //  Step 2: build the graph
        for (int i = 0; i < edges.length; i++) {
            int parent = edges[i][0];
            int child = edges[i][1];
            graph.get(parent).add(child);
            inDegree.put(child, inDegree.get(child) + 1);
        }

        // Step 3: find all sources: vertex (node) with no incoming edge is source
        Queue<Integer> sources = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                sources.add(entry.getKey());
            }
        }

        // Step 4: topological sort: For each source, add it to the sortedOrder and subtract one from all of its
        // children's in-degrees. If a child's in-degree becomes zero, add it to sources queue
        while (!sources.isEmpty()) {
            int vertex = sources.poll();
            sortedOrder.add(vertex);
            List<Integer> children = graph.get(vertex);
            children.forEach(child -> {
                inDegree.put(child, inDegree.get(child) - 1);
                if (inDegree.get(child) == 0) {
                    sources.add(child);
                }
            });
        }

        if (sortedOrder.size() != vertices) {
            // Tolological sort is not possible as the graph has cycle.
            return new ArrayList<>();
        }

        return sortedOrder;
    }

    public static void main(String[] args) {
        List<Integer> result = TopologicalSort.sort(4, new int[][] { new int[] { 3, 2 },
                new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
        System.out.println(result);

     result = TopologicalSort.sort(5, new int[][] { new int[] { 4, 2 },
                new int[] { 4, 3 }, new int[] { 2, 0 }, new int[] { 2, 1 }, new int[] { 3, 1 } });
        System.out.println(result);

        result = TopologicalSort.sort(7, new int[][] { new int[] { 6, 4 },
                new int[] { 6, 2 }, new int[] { 5, 3 }, new int[] { 5, 4 },
                new int[] { 3, 0 }, new int[] { 3, 1 }, new int[] { 3, 2 }, new int[] { 4, 1 } });
        System.out.println(result);
    }
}
