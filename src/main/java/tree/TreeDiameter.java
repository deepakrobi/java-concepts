package tree;

public class TreeDiameter {

    static int treeDiameter = 0;
    public static int findDiameter(TreeNode root){
        findDiameterRecursive(root);
        return treeDiameter;
    }

    private static int findDiameterRecursive(TreeNode currentNode){
        if(currentNode == null){
            return 0;
        }
        int leftTreeHeight = findDiameterRecursive(currentNode.left);
        int rightTreeheight = findDiameterRecursive(currentNode.right);

        if(currentNode.left != null && currentNode.right != null){
            // if it is a lef node, we can;t have a path passing through it. For a diameter, we need a leaf node on each side.
            // hence diameter calcualtion canbe ignored for lef node
            int diameter = leftTreeHeight + rightTreeheight + 1;
            treeDiameter = Math.max(treeDiameter,diameter);
        }

        return Math.max(leftTreeHeight, rightTreeheight) + 1;
    }

    public static void main (String [] args){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println("Tree Diameter: " + TreeDiameter.findDiameter(root));
        treeDiameter = 0;
        root.left.left = null;
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        root.right.left.right.left = new TreeNode(10);
        root.right.right.left.left = new TreeNode(11);
        System.out.println("Tree Diameter: " + TreeDiameter.findDiameter(root));
    }
}
