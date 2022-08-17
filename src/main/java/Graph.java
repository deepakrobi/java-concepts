import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

class Node {
    int value;
    String name;
    boolean visited;

    Node(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
     void visit(){
        visited = true;
     }
     void unvisit(){
        visited = false;
     }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if ((obj instanceof Node) && ((Node) obj).getName() != null && ((Node) obj).getName().equals(this.name)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

public class Graph {
    // implement an adjacencyMap. Each node maps to a list of all of his neighbors
    private HashMap<Node, LinkedList<Node>> adjacencyMap;
    private boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        adjacencyMap = new HashMap<>();
    }

    public void addEdge(Node source, Node destination) {
        if (!adjacencyMap.keySet().contains(source)) {
            adjacencyMap.put(source, null);
        }
        if (!adjacencyMap.keySet().contains(destination)) {
            adjacencyMap.put(destination, null);
        }
        addEdgeToNode(source, destination);
        if (!directed) {
            addEdgeToNode(destination, source);
        }
    }

    private void addEdgeToNode(Node source, Node destination) {
        LinkedList<Node> tmp = adjacencyMap.get(source);
        if (tmp != null) {
            tmp.remove(destination);
        } else {
            tmp = new LinkedList<>();
        }
        tmp.add(destination);
        adjacencyMap.put(source, tmp);
    }

    public void printEdges() {
        String seperator = " -> ";
        if(!directed){
            seperator = " - ";
        }
        for (Node node : adjacencyMap.keySet()) {
            System.out.print("The " + node.getName() + " has and edge towards : ");
            if (adjacencyMap.get(node) != null) {
                for (Node neighbor : adjacencyMap.get(node)) {
                    System.out.print(  seperator  +neighbor.name + "");
                }
                System.out.println();
            } else {
                System.out.println("none");
            }
        }
    }

    public boolean hasEdge(Node source, Node destination) {
        return (adjacencyMap.containsKey(source) && adjacencyMap.get(source) != null
                        && adjacencyMap.get(source).contains(destination));
    }

    public void resetNodeVisited(){
        for (Node node : adjacencyMap.keySet()){
            node.unvisit();
        }
    }

    public void topoSort() {
        Stack<Node> nodeStack = new Stack<>();
        for (Node node : adjacencyMap.keySet()) {
            if (!node.visited) {
               topoSortUtil(node,nodeStack);
            }
        }
        System.out.println(" Topological sort order for the graph >>>>>>>>>>>");
        while (!nodeStack.isEmpty()){
            System.out. print(nodeStack.pop().name + " - ");
        }
    }

    private void topoSortUtil(Node node, Stack<Node> nodeStack) {
        if (node.visited) {
            return;
        }
        node.visit();
        if (adjacencyMap.get(node) != null) {
            for (Node neighbor : adjacencyMap.get(node)) {
                if(!neighbor.visited){
                    topoSortUtil(neighbor,nodeStack);
                }
            }
        }
        nodeStack.push(node);
    }

    public void depthFirstSearch(Node node) {
        if (node == null || node.visited) {
            return;
        }
        node.visit();
        System.out.print(" " + node.getName());
        LinkedList<Node> neighbors = adjacencyMap.get(node);
        if (neighbors == null) {
            return;
        }
        for (Node neighbor : neighbors) {
            if (!neighbor.visited) {
                depthFirstSearch(neighbor);
            }
        }
    }

    public void depthFirstSearchAll(Node node) {
        depthFirstSearch(node);
        for (Node n : adjacencyMap.keySet()) {
            if (!n.visited) {
                depthFirstSearch(n);
            }
        }
    }

    public void breadthFirstSearchAll(Node node) {
        breadthFirstSearch(node);
        for (Node n : adjacencyMap.keySet()) {
            if (!n.visited) {
                breadthFirstSearch(n);
            }
        }
    }

    public void breadthFirstSearch(Node node) {
        if (node == null) return;
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node current = queue.removeFirst();
            if (current.visited) {
                continue;
            }
            current.visit();
            System.out.print(" " + current.getName());
            if (adjacencyMap.get(current) == null) {
                continue;
            }
            for (Node neighbor : adjacencyMap.get(current)) {
                if (!neighbor.visited) {
                    queue.add(neighbor);
                }
            }
        }
    }
}

class GraphMatrix {
    int numOfNodes;
    private boolean directed;
    private boolean weighted;
    private float[][] matrix;
    private boolean[][] isSetMatrix;

    public GraphMatrix(int numOfNodes, boolean directed, boolean weighted) {
        this.numOfNodes = numOfNodes;
        this.directed = directed;
        this.weighted = weighted;
        matrix = new float[numOfNodes][numOfNodes];
        isSetMatrix = new boolean[numOfNodes][numOfNodes];
    }

    public void addEdge(int source, int destination, float weight) {
        float valueToAdd = weight;
        if (!weighted) {
            valueToAdd = 1;
        }
        matrix[source][destination] = valueToAdd;
        isSetMatrix[source][destination] = true;
        if (!directed) {
            matrix[destination][source] = valueToAdd;
            isSetMatrix[destination][source] = true;
        }
    }

    public void addEdge(int source, int destination) {
        float weight = 1;
        if (weighted) {
            weight = 0;
        }
        addEdge(source, destination, weight);
    }

    public  void printMatrix(){
        for(int i=0 ; i <numOfNodes ; i++){
            for (int j=0 ; j <numOfNodes ; j++){
                if(isSetMatrix[i][j]){
                    System.out.format("%8s", String.valueOf(matrix[i][j]));
                }else{
                    System.out.format("%8s", "/  ");
                }
                System.out.println();
            }
        }
    }

    public void printEdges(){
        for(int i=0;i<numOfNodes ; i++){
            System.out.print("Node " + i + " is connected to: ");
            for(int j=0;j<numOfNodes ; j++) {
                if (isSetMatrix[i][j]){
                    System.out.print( j + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean hasEdge(int source, int destination) {
        return isSetMatrix[source][destination];
    }

    public Float getEdgeValue(int source, int destination) {
        if (!weighted || !isSetMatrix[source][destination])
            return null;
        return matrix[source][destination];
    }
}
class GraphShow {
    public static void main (String [] args){
        Graph graph = new Graph(false);
        Node a = new Node(0, "0");
        Node b = new Node(1, "1");
        Node c = new Node(2, "2");
        Node d = new Node(3, "3");
        Node e = new Node(4, "4");

        graph.addEdge(a,d);
        graph.addEdge(a,b);
        graph.addEdge(c,e);

        graph.printEdges();
        //System.out.println(graph.hasEdge(a,b));
       // System.out.println(graph.hasEdge(d,a));
        System.out.println( " Traversing graph using DFS : >>>>");
        graph.depthFirstSearch(b);
        graph.resetNodeVisited();
        System.out.println();
        System.out.println( " Traversing all graph using DFS : >>>>");
        graph.depthFirstSearchAll(b);
        graph.resetNodeVisited();
        System.out.println();
        System.out.println( " Traversing graph using BFS : >>>>");
        graph.breadthFirstSearch(a);
        graph.resetNodeVisited();
        System.out.println();
        System.out.println( " Traversing all graph using BFS : >>>>");
        graph.breadthFirstSearchAll(a);
        graph.resetNodeVisited();
        System.out.println();
        System.out.println( " Topological sort of the graph is  : >>>>");

        Graph graphSort = new Graph(true);
        Node a1 = new Node(0, "0");
        Node b1 = new Node(1, "1");
        Node c1 = new Node(2, "2");
        Node d1 = new Node(3, "3");
        Node e1 = new Node(4, "4");
        Node f1 = new Node(5, "5");

        graphSort.addEdge(f1, b1);
        graphSort.addEdge(f1, a1);
        graphSort.addEdge(e1, a1);
        graphSort.addEdge(e1, b1);
        graphSort.addEdge(c1, d1);
        graphSort.addEdge(d1, a1);

        graphSort.topoSort();
    }
}