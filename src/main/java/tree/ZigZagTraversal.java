package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigZagTraversal {
    public static List<List<Integer>> traverse(Node root){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
         if (root == null){
             return result;
         }
        Queue<Node> queue = new LinkedList<>();
         queue.add(root);
         while (!queue.isEmpty()) {
             boolean leftToRight = true;
             int levelSize = queue.size();
             List<Integer> currentLevel = new ArrayList<>(levelSize);
             for (int i = 0; i < levelSize; i++) {
                Node visitedNode = queue.remove();
                if(leftToRight){
                    currentLevel.add(visitedNode.value);
                }else{
                    currentLevel.add(0,visitedNode.value);
                }
                if(visitedNode.left != null){
                    queue.add(visitedNode.left);
                }
                if(visitedNode.right != null){
                    queue.add(visitedNode.right);
                }
             }
             result.add(currentLevel);
             leftToRight = !leftToRight;
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
        List<List<Integer>> result = ZigZagTraversal.traverse(root);
        System.out.println("Zigzag level order traversal:");
        for(int i = 0; i < result.size(); i++) {
            System.out.println( result.get(i));
        }
    }

}
