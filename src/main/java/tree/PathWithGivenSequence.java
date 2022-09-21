package tree;

/**
 * Problem Statement
 * Given a binary tree and a number sequence, find if the sequence is present as a root-to-leaf path in the given tree.
 */
public class PathWithGivenSequence {

    public static boolean hasPath(TreeNode root, int[] sequence) {
        if (root == null) {
            return sequence.length == 0;
        }
        return hasPathRecursive(root, sequence, 0);
    }

    public static boolean hasPathRecursive(TreeNode currentNode, int[] sequence, int sequenceIndex) {
        if (currentNode == null) {
            return false;
        }

        if (sequenceIndex >= sequence.length || currentNode.val != sequence[sequenceIndex]) {
            return false;
        }

        if (currentNode.left == null && currentNode.right == null && sequenceIndex == sequence.length - 1) {
            // this is leaf node and sequence has also reached to end without returning false earlier.
            return true;
        }

        return hasPathRecursive(currentNode.left, sequence, sequenceIndex + 1) ||
                hasPathRecursive(currentNode.right, sequence, sequenceIndex + 1);

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

        System.out.println("Tree has path sequence: " +
                PathWithGivenSequence.hasPath(root, new int[] { 1, 0, 7 }));
        System.out.println("Tree has path sequence: " +
                PathWithGivenSequence.hasPath(root, new int[] { 1, 1, 6 }));
    }
}
