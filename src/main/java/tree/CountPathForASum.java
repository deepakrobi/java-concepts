package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Problem Statement
 *
 * Given a binary tree and a number ‘S’, find all paths in the tree such that the sum of all the node values of each path equals ‘S’.
 * Please note that the paths can start or end at any node but all paths must follow direction from parent to child (top to bottom).
 */
public class CountPathForASum {

    public static int countPaths(TreeNode root, int S) {
        List<TreeNode> currentPath = new LinkedList<>();
        return countPathRecursive(root,currentPath,S);
    }

    public static int countPathRecursive(TreeNode currentNode, List<TreeNode> currentpath, int s) {
        int count = 0, pathSum = 0;
        if (currentNode == null) {
            return count;
        }
        currentpath.add(currentNode);
        ListIterator<TreeNode> pathIterator = currentpath.listIterator(currentpath.size());
        while (pathIterator.hasPrevious()) {
            pathSum += pathIterator.previous().val;
            if (pathSum == s) {
                count++;
            }
        }

        count += countPathRecursive(currentNode.left, currentpath, s);
        count += countPathRecursive(currentNode.right, currentpath, s);

        // This is need to remove the current node while we are going up the recursive call stack.
        currentpath.remove(currentpath.size() - 1);

        return count;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has path: " + CountPathForASum.countPaths(root, 11));
    }
}
