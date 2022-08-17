package GraphWeighted;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class Edge implements Comparable<Edge> {
    Node source;
    Node destination;
    Double weight;

    public Edge(Node source, Node destination, Double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight != null ? weight : 1.0;
    }

    public int compareTo(Edge otherEdge) {
        if (this.weight > otherEdge.weight) {
            return 1;
        }
        return -1;
    }

    public String toString() {
        return String.format("(%s -> %s, %f)", source.name, destination.name, weight);
    }
}

class Node {
    String name;
    boolean visited;
    PriorityQueue<Edge> edges;

    public Node(String name) {
        this.name = name;
        visited = false;
        edges = new PriorityQueue<>();
    }

    public void addEdge(Node destination, double weight) {
        if (!edges.contains(destination)) {
            this.edges.add(new Edge(this, destination, weight));
        }
    }
    public boolean isVisited(){
        return  visited;
    }
    public void visit(){
        visited = true;
    }
    public void unvisit(){
        visited = false;
    }
    public PriorityQueue<Edge> edges(){
        return  edges;
    }
}

class Graph {
    Set<Node> nodes;
    boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        nodes = new HashSet<>();
    }

    public void addNode(Node... node) {
        nodes.addAll(Arrays.asList(node));
    }

    public void addEdge(Node source, Node destination, Double weight) {
        nodes.add(source);
        nodes.add(destination);
        source.addEdge(destination, weight);
        if (!directed && source != destination) {
            destination.addEdge(source, weight);
        }
    }

    public void printEdges() {
        for (Node node : nodes) {
            System.out.print("Node " + node.name + " has edges to : ");
            for (Edge edge : node.edges()) {
                System.out.print(" " + edge.destination.name + " ( " + edge.weight + " )");
            }
            System.out.println();
        }
    }

    public void resetNodesVisited() {
        for (Node node : nodes) {
            node.unvisit();
        }
    }

    public void dijkstraShortestPath(Node source, Node destination) {
        HashMap<Node, Node> previousVertex = new HashMap<>();
        HashMap<Node, Double> shortestPathFromSource = new HashMap<>();
        previousVertex.put(source, null);
        shortestPathFromSource.put(source, 0.0);

        for (Node node : nodes) {
            if (node != source) {
                shortestPathFromSource.put(node, Double.POSITIVE_INFINITY);
            }
        }
        // initialize the edges from the source node
        for (Edge edge : source.edges()) {
            shortestPathFromSource.put(edge.destination, edge.weight);
            previousVertex.put(edge.destination, source);
        }
        source.visit();
        while (true) {
            Node currentNode = closestUnvisited(shortestPathFromSource);
            if (currentNode == null) {
                System.out.println("There isn't a path between " + source.name + " and " + destination.name);
                return;
            }
            if (currentNode == destination) {
                System.out.print("Shortest Path from " + source + " to " + destination + "is :");
                Node child = destination;
                String path = destination.name;
                while (true) {
                    Node parent = previousVertex.get(child);
                    if (parent == null) {
                        break;
                    }

                    path = parent.name + " -> " + path;
                    child = parent;
                }
            }

            currentNode.visit();

            for (Edge edge : currentNode.edges()) {
                if (edge.destination.isVisited()) {
                    continue;
                }
                if (shortestPathFromSource.get(currentNode) + edge.weight
                        < shortestPathFromSource.get(edge.destination)) {
                    shortestPathFromSource.put(edge.destination, shortestPathFromSource.get(currentNode) + edge.weight);
                    previousVertex.put(edge.destination, currentNode);
                }
            }
        }
    }

    private Node closestUnvisited(HashMap<Node, Double> shortesPathFromSource) {
        double shortestDistance = Double.POSITIVE_INFINITY;
        Node closestVisited = null;
        for (Node node : nodes) {
            if (node.isVisited()) {
                continue;
            }
            double currentDistance = shortesPathFromSource.get(node);
            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance;
                closestVisited = node;
            }
        }
        return closestVisited;
    }
}
public class GraphConcepts {

    public  static void main ( String [] args) {
        Graph graph = new Graph(true);
        Node zero = new Node("0");
        Node one = new Node("1");
        Node two = new Node("2");
        Node three = new Node("3");
        Node four = new Node("4");
        Node five = new Node("5");
        Node six = new Node("6");

        graph.addEdge(zero, one, 8.0);
        graph.addEdge(zero, two, 11.0);
        graph.addEdge(one, three, 3.0);
        graph.addEdge(one, four, 8.0);
        graph.addEdge(one, two, 7.0);
        graph.addEdge(two, four, 9.0);
        graph.addEdge(three, four, 5.0);
        graph.addEdge(three, five, 2.0);
        graph.addEdge(four, six, 6.0);
        graph.addEdge(five, four, 1.0);
        graph.addEdge(five, six, 8.0);
        graph.printEdges();
    }
}
