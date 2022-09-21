package tree;

public class TreePathSum {

    public static boolean hasPath(Node root, int sum) {
        if(root == null){
            return  false;
        }
        if(root.value == sum && root.left == null && root.right == null){
            return true;
        }
        return hasPath(root.left,sum-root.value) || hasPath(root.right,sum- root.value);
    }

    public static void main(String[] args) {
        Node root = new Node(12);
        root.left = new Node(7);
        root.right = new Node(1);
        root.left.left = new Node(9);
        root.right.left = new Node(10);
        root.right.right = new Node(5);
        System.out.println("Tree has path: " + TreePathSum.hasPath(root, 23));
        System.out.println("Tree has path: " + TreePathSum.hasPath(root, 16));
    }
}
