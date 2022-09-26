package slidingwindow.TopologicalSort;

import java.util.ArrayList;
import java.util.List;

class Node {
    private int id;
    private List<Integer> neighbors;

    public Node(int id) {
        this.id = id;
        this.neighbors = new ArrayList<>();
    }

    public void addNeighbor(int e) {
        this.neighbors.add(e);
    }

    public int getId() {
        return id;
    }

    public List<Integer> getNeighbors() {
        return neighbors;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", neighbors=" + neighbors +
                "}" + "\n";
    }
}

 class Graph {
    private List<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void addNode(Node e) {
        this.nodes.add(e);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Node getNode(int searchId) {
        for (Node node:this.getNodes()) {
            if (node.getId() == searchId) {
                return node;
            }
        }
        return null;
    }

    public int getSize() {
        return this.nodes.size();
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + nodes +
                "}";
    }
}

public class TopologicalSort {
    public static void main(String[] args) {
        Graph g = new Graph();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.addNeighbor(2);
        node2.addNeighbor(3);
        node4.addNeighbor(3);
        g.addNode(node1);
        g.addNode(node2);
        g.addNode(node3);
        g.addNode(node4);
        System.out.println(g);
    }
}
