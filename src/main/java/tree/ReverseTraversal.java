package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
Given a binary tree, populate an array to represent its level-by-level traversal in reverse order, i.e., the lowest level comes first. You should populate the values of all nodes in each level from left to right in separate sub-arrays.

Example 1
             1
            / \
           2   3
          /\   /\
         4 5   6 7

 Output:
 Reverse level order traversal:
 [4, 5, 6, 7 ]
 [2,3]
 [1]
 */
public class ReverseTraversal {

    public static List<List<Integer>> traverse(Node root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<Node> queue = new LinkedList<>();
        if (root == null) {
            return result;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                Node visitedNode = queue.remove();
                currentLevel.add(visitedNode.value);
                if (visitedNode.left != null) {
                    queue.add(visitedNode.left);
                }
                if (visitedNode.right != null) {
                    queue.add(visitedNode.right);
                }
            }
            result.add(0, currentLevel);
        }
        return result;
    }

    public static void main(String[] args) {
        Node root = new Node(12);
        root.left = new Node(7);
        root.right = new Node(1);
        root.left.left = new Node(9);
        root.right.left = new Node(10);
        root.right.right = new Node(5);
        List<List<Integer>> result = ReverseTraversal.traverse(root);
        System.out.println("Reverse level order traversal:");
        for(int i = 0; i < result.size(); i++) {
            System.out.println( result.get(i));
        }
    }
}
