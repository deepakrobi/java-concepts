package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelAverages {

    public static List<Double> findLevelAvgs(Node root){
        List<Double> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<Node>  queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            Double sum = 0.0;
            for (int i =0;i<levelSize;i++){
                Node visitedNode = queue.remove();
                sum+=visitedNode.value;
                if(visitedNode.left != null){
                    queue.add(visitedNode.left);
                }
                if(visitedNode.right != null){
                    queue.add(visitedNode.right);
                }
            }
                result.add(sum/levelSize);
        }

        return  result;
    }

    public static void main(String[] args) {
        Node root = new Node(12);
        root.left = new Node(7);
        root.right = new Node(1);
        root.left.left = new Node(9);
        root.left.right = new Node(2);
        root.right.left = new Node(10);
        root.right.right = new Node(5);
        List<Double> result = LevelAverages.findLevelAvgs(root);
        System.out.print("Level averages are: " + result);
    }
}
