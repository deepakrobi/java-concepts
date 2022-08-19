package practice.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

class Node implements Comparable<Node>{
    String name;
    int value;
    boolean visited;
    LinkedList<Node> adjacentNodes;

    public Node( int value, String name) {
        this.name = name;
        this.value = value;
        visited = false;
        adjacentNodes = new LinkedList<>();
    }

    public Node(String name) {
        this(1,name);
    }

    public void visit() {
        visited = true;
    }

    public void unvisit() {
        visited = false;
    }

    public void addEdge(Node destination) {
        if (!adjacentNodes.contains(destination)) {
            adjacentNodes.add(destination);
        }
    }

    public int compareTo(Node otherNode) {
        return this.value - otherNode.value;
    }
}

class Graph {
    Set<Node> nodes;
    boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        nodes = new HashSet<>();
    }

    public void resetVisitedNodes() {
        for (Node node : nodes) {
            node.unvisit();
        }
    }

    public void addEdge(Node source, Node destination) {
        if (source == null || destination == null) {
            throw new RuntimeException("Source or destination can not be null");
        }
        nodes.add(source);
        nodes.add(destination);
        source.addEdge(destination);
        if (!directed && source != destination) {
            destination.addEdge(source);
        }
    }

    public void printEdges() {
        for (Node node : nodes) {
            System.out.print("Node " + node.name + " has edges towards : ");
            if (node.adjacentNodes == null || node.adjacentNodes.isEmpty()) {
                System.out.print(" none");
            }
            for (Node n : node.adjacentNodes) {
                System.out.print(" " + n.name);
            }
            System.out.println();
        }
    }

    public void depthFirstSearch(Node node) {
        if (node == null || node.visited) {
            return;
        }
        node.visit();
        System.out.print(" " + node.name);
        node.adjacentNodes.stream().filter(n -> !n.visited).forEach(this::depthFirstSearch);
    }

    public void depthFirstSearchAll(Node node) {
        depthFirstSearch(node);
        nodes.stream().filter(n -> !n.visited).forEach(this::depthFirstSearch);
    }

    public void breadthFirstSearch(Node node) {
        if (node == null || node.visited) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node currentNode = queue.removeFirst();
            if (currentNode.visited) {
                continue;
            }

            System.out.print(" " + currentNode.name);
            currentNode.visit();
            currentNode.adjacentNodes.stream().filter(n -> !n.visited).forEach(queue::add);
        }
    }

    public void findShortestPath(Node source, Node destination) {
        Map<Node,Node> previousVertex = new HashMap<>();
        previousVertex.put(source,null);

        if (source == null || source.visited) {
            return;
        }

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(source);
        boolean found = false;
        String path;

        while (!queue.isEmpty()) {
            Node currentNode = queue.removeFirst();
            if (currentNode.visited) {
                continue;
            }
            currentNode.visit();

            if(currentNode == destination){
                found = true;
                break;
            }

            for(Node node : currentNode.adjacentNodes){
                if(!node.visited){
                    previousVertex.put(node,currentNode);
                    queue.add(node);
                }
            }
        }

        System.out.println();
        if (!found) {
            System.out.println("No path found from source : " + source.name + " to " + destination.name);
            return;
        } else {
            System.out.println("Shortest distance from source : " + source.name + " to " + destination.name + " is : " );
            path = destination.name;
            Node child = destination;
            while (true) {
                Node parent = previousVertex.get(child);
                if (parent == null) {
                    break;
                }
                path = parent.name + " -> " + path;
                child = parent;
            }
            System.out.println(path);
        }
    }

    public void breadthFirstSearchAll(Node node) {
        breadthFirstSearch(node);
        nodes.stream().filter(n -> !n.visited).forEach(this::breadthFirstSearch);
    }

    public void topoSort() {
        Stack<Node> stack = new Stack<>();
        nodes.stream().filter(n -> !n.visited).forEach(unvisitedNode -> pushToStack(unvisitedNode, stack));
        while (!stack.isEmpty()) {
            System.out.print(" " + stack.pop().name);
        }
    }

    private void pushToStack(Node node, Stack stack) {
        if (node == null || node.visited) {
            return;
        }
        node.visit();
        node.adjacentNodes.stream().filter(n -> !n.visited).forEach(n -> pushToStack(n, stack));
        stack.push(node);
    }
}

public class GraphAlgo {

    public static void main (String [] args){
        Graph graph = new Graph(true);
        Node a = new Node(0, "A");
        Node b = new Node(1, "B");
        Node c = new Node(2, "C");
        Node d = new Node(3, "D");
        Node e = new Node(4, "E");

        graph.addEdge(a,b);
        graph.addEdge(b,c);
        graph.addEdge(b,d);
        graph.addEdge(c,e);
        graph.addEdge(b,a);

        graph.printEdges();
        GraphAlgo graphAlgo = new GraphAlgo();
        graphAlgo.dfsSearch();
        graphAlgo.dfsSearchAll();
        graphAlgo.bfsSearch();
    }

    public void dfsSearch() {
        System.out.println("Traversing graph using dfs");
        Graph graph = new Graph(true);
        Node zero = new Node(0, "0");
        Node one = new Node(1, "1");
        Node two = new Node(2, "2");
        Node three = new Node(3, "3");
        Node four = new Node(4, "4");
        Node five = new Node(5, "5");
        Node six = new Node(6, "6");
        Node seven = new Node(7, "7");
        Node eight = new Node(8, "8");

        graph.addEdge(one, zero);
        graph.addEdge(three, one);
        graph.addEdge(two, seven);
        graph.addEdge(two, four);
        graph.addEdge(five, two);
        graph.addEdge(five, zero);
        graph.addEdge(six, five);
        graph.addEdge(six, three);
        graph.addEdge(six, eight);
        graph.addEdge(seven, five);
        graph.addEdge(seven, six);
        graph.addEdge(seven, eight);
        graph.depthFirstSearch(seven);
    }

    public void dfsSearchAll() {

        Graph graph = new Graph(false);
        Node a = new Node(0, "0");
        Node b = new Node(1, "1");
        Node c = new Node(2, "2");
        Node d = new Node(3, "3");
        Node e = new Node(4, "4");


        graph.addEdge(a,b);
        graph.addEdge(a,c);
        graph.addEdge(c,b);
        graph.addEdge(e,d);

        System.out.println("If we were to use our previous DFS method, we would get an incomplete traversal");
        graph.depthFirstSearch(b);
        graph.resetVisitedNodes(); // All nodes are marked as visited because of
        // the previous DFS algorithm so we need to
        // mark them all as not visited

        System.out.println();
        System.out.println("Using the modified method visits all nodes of the graph, even if it's unconnected");
        graph.depthFirstSearchAll(b);

    }
    public void bfsSearch() {
        Graph graph = new Graph(false);
        Node a = new Node(0, "a");
        Node b = new Node(1, "b");
        Node c = new Node(2, "c");
        Node d = new Node(3, "d");
        Node e = new Node(4, "e");

        graph.addEdge(a,b);
        graph.addEdge(a,d);
        graph.addEdge(c,e);
        graph.addEdge(b,e);

        System.out.println();
        System.out.println("Using the unmodified version of BFS we get:");
        graph.breadthFirstSearch(a);
        graph.resetVisitedNodes();
        System.out.println();
        System.out.println("Using the modified version of BFS we get:");
        graph.breadthFirstSearchAll(a);
        graph.resetVisitedNodes();
        graph.findShortestPath(a,c);
    }
}
