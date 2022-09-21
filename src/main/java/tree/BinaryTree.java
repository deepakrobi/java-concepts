package tree;

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTree {
    Node root;

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }
        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }
        return current;
    }

    private boolean containsRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }
        if(value == current.value){
            return true;
        }
        return value < current.value
                ? containsRecursive(current.left, value)
                : containsRecursive(current.right, value);
    }

    public boolean contains(int value){
        return containsRecursive(root,value);
    }

    public void add(int value) {
        root = addRecursive(root, value);
       // System.out.println(" root is : " + root.value);
    }

    private  int maxLevel(Node node) {
        if (node == null)
            return 0;

        return Math.max(this.maxLevel(node.left), this.maxLevel(node.right)) + 1;
    }

    private void printNodes(Node current){
        if(current.left != null){
            System.out.println(" ");
            System.out.println(" /");
            System.out.println(" " +current.left.value);
            printNodes(current.left);
        }
        if(current.right != null){
            System.out.println(" ");
            System.out.println(" \\");
            System.out.println(current.right.value);
            printNodes(current.right);
        }
    }

    public void printTree (){
        if (root== null){
            System.out.print(":( its empty here");
        }

        int maxlevel = maxLevel(root);
        ///System.out.println( " Max level is : "+ maxlevel);
        for (int i = 0; i < maxlevel*2; i++) {
            System.out.print(" ");
        }
        System.out.print(root.value);
        //printNodes(root);

}

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.add(6);
        binaryTree.add(4);
        binaryTree.add(2);
        binaryTree.add(5);
        binaryTree.add(8);

        int value = 6;
        System.out.println(" find "+value + " : "+ binaryTree.contains(value));
        binaryTree.printTree();
    }
}
