package topologialsort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AllTasksScheduling {

    public static List<List<Integer>> getAllOrders(int tasks, int [] [] edges){
        List<List<Integer>> orders = new <List<String>>ArrayList();

        if(tasks ==0){
            return orders;
        }

        // Graph with adjacency list
        Map<Integer,List<Integer>> graph = new <Integer, List<Integer>>HashMap();
        // count dependency ( number of incoming edges) for each tasks
        Map<Integer,Integer> inDegree = new HashMap<>();

        // Step1: initialize the graph
        for(int i =0; i< tasks ; i ++){
            graph.put(i,new ArrayList<>());
            inDegree.put(i,0);
        }

        // Step 2: build the graph
        for (int i =0; i< edges.length; i++) {
            int parent = edges[i][0];
            int child = edges[i][1];
            graph.getOrDefault(parent, new ArrayList<>()).add(child);
            inDegree.put(child, inDegree.getOrDefault(child, 0) + 1);
        }

        //Step 3: find all sources i.e., all vertices or tasks with 0 in-degree.
        Queue<Integer> sources = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if(entry.getValue() ==0){
                sources.add(entry.getKey());
            }
        }

        // Step 4: topological sort: find all possible sort paths.
        List<Integer> sortedOrder = new ArrayList<>();
        populateAllTopologicalSortOrders(graph,inDegree,sources,sortedOrder, orders);

        return orders;
    }

    private static void populateAllTopologicalSortOrders(Map<Integer, List<Integer>> graph, Map<Integer, Integer> inDegree,
                                                         Queue<Integer> sources, List<Integer> sortedOrder, List<List<Integer>> orders) {
        if (!sources.isEmpty()) {
            for (Integer task : sources) {
                sortedOrder.add(task);
                Queue<Integer> sourcesForNextCall = new LinkedList<>(sources);//cloneQueue(sources);
                 sourcesForNextCall.remove(task);
                List<Integer> children = graph.get(task);
                children.forEach(child -> {
                    inDegree.put(child, inDegree.get(child) - 1);
                    if (inDegree.get(child) == 0) {
                        sourcesForNextCall.add(child);
                    }
                });

                populateAllTopologicalSortOrders(graph, inDegree, sourcesForNextCall, sortedOrder,orders);

                // backtrack, remove the vertex from the sorted order and pull all its children back to be considered
                sortedOrder.remove(task);
                children.forEach(child -> {
                    inDegree.put(child, inDegree.get(child) + 1);
                });
            }
        }
        if (sortedOrder.size() == inDegree.size()) {
           // System.out.println(sortedOrder);
            orders.add(new ArrayList<>(sortedOrder));
        }
    }

    private static Queue<Integer> cloneQueue (Queue<Integer> sources) {
        Queue<Integer> clone = new LinkedList<>();
        for (Integer num : sources) {
            clone.add(num);
        }
        return clone;
    }

    public static void main(String[] args) {
        System.out.println( AllTasksScheduling.getAllOrders(3,
                new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } }));
        System.out.println();

        System.out.println(AllTasksScheduling.getAllOrders(4, new int[][] { new int[] { 3, 2 },
                new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } }));
        System.out.println();

        System.out.println(AllTasksScheduling.getAllOrders(6,
                new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 }, new int[] { 0, 4 },
                        new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } }));
        System.out.println();
    }
}
