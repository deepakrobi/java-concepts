package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

class Node {
    int value;
    String name;
    boolean visited;
    LinkedList<Node> adjacentNodes;

    public Node(int value, String name) {
        this.value = value;
        this.name = name;
        adjacentNodes = new LinkedList<>();
        visited = false;
    }

    public void addEdge(Node node) {
        if (!this.adjacentNodes.contains(node)) {
            this.adjacentNodes.add(node);
        }
    }

    public LinkedList<Node> adjacentNodes() {
        return adjacentNodes;
    }

    public String name() {
        return this.name;
    }

    public void visit(){
        visited = true;
    }

    public void unvisit(){
        visited = false;
    }
}

class Graph {
    Set<Node> nodes;
    boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        nodes = new HashSet<>();
    }

    public void addEdge(Node source, Node destination) {
       nodes.add(source);
       nodes.add(destination);
        // addEdgeToNode(source, destination);
        source.addEdge(destination);
        if (!directed) {
            //  addEdgeToNode(destination, source);
            destination.addEdge(source);
        }
    }

    public void printEdges() {
        for (Node node : nodes) {
            System.out.print("The " + node.name() + " has and edge towards : ");
            if (node.adjacentNodes() != null && !node.adjacentNodes().isEmpty()) {
                for (Node adjacentNode : node.adjacentNodes()) {
                    System.out.print(" " + adjacentNode.name());
                }
                System.out.println();
            } else {
                System.out.println(" none ");
            }
        }
    }

    private void addEdgeToNode(Node source, Node destination) {
        LinkedList<Node> tmp = source.adjacentNodes();
        if (tmp == null) {
            tmp = new LinkedList<>();
        } else {
            tmp.remove(destination);
        }
        tmp.add(destination);
    }

    public void markAllNodesUnvisited() {
        for (Node node : nodes) {
            node.unvisit();
        }
    }

    public void depthFirstSearch(Node node) {
        if (node == null || node.visited) {
            return;
        }
        node.visit();
        System.out.print(" " + node.name());
        if (node.adjacentNodes() != null && !node.adjacentNodes.isEmpty()) {
            for (Node adjacentNode : node.adjacentNodes()) {
                if (!adjacentNode.visited) {
                    depthFirstSearch(adjacentNode);
                }
            }
        }
    }
    public void pushToStack(Node node, Stack<Node> stack) {
        if (node.visited) {
            return;
        }
        node.visit();
        if (node.adjacentNodes() != null) {
            for (Node n : node.adjacentNodes()) {
                if (!n.visited) {
                    pushToStack(n, stack);
                }
            }
        }
        stack.push(node);
    }

    public void depthFirstSearchAll(Node node) {
        depthFirstSearch(node);
        for (Node n : nodes) {
            if (!n.visited) {
                depthFirstSearch(n);
            }
        }
    }
    public void topologicalSort() {
        Stack<Node> nodeStack = new Stack<>();
        for (Node node : nodes) {
            if (!node.visited) {
                // push the dependent node in stack
                pushToStack(node, nodeStack);
            }
        }
        while (!nodeStack.isEmpty()) {
            System.out.print(" " + nodeStack.pop().name());
        }
    }

    public void breadthFirstSearch(Node node) {
        if (node == null || node.visited){
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            Node n = queue.removeFirst();
            if(n.visited) {
                continue;
            }
            n.visit();
            System.out.print(" "+node.name());
            if(n.adjacentNodes() != null && !n.adjacentNodes().isEmpty()){
               /* n.getAdjacentNodes().stream().filter(nonVistsedNode-> !n.visited).forEach(node1 -> {
                    queue.add(node1);
                        }
                );*/
                for(Node adjacentNode: n.adjacentNodes()){
                    if(!adjacentNode.visited){
                        queue.add(adjacentNode);
                    }
                }
            }
        }
    }

    public void breadthFirstSearchAll(Node node){
        breadthFirstSearch(node);
        for(Node n : nodes){
            if(!n.visited){
                breadthFirstSearch(n);
            }
        }
    }

}
public class GraphConcepts {
    public static void main(String[] args) {
        Graph graph = new Graph(false);
        Node a = new Node(0, "A");
        Node b = new Node(1, "B");
        Node c = new Node(2, "C");
        Node d = new Node(3, "D");
        Node e = new Node(4, "E");

        graph.addEdge(a, b);
        graph.addEdge(b, c);
        graph.addEdge(b, d);
        graph.addEdge(c, e);
        graph.addEdge(b, a);

        graph.printEdges();
    }
}
