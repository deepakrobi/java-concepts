package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * GIven a binary Seacrh tree perform in-order traversal
 *                  10
 *                 /  \
 *                4    15
 *               /     / \
 *              1    14   19
 *                          \
 *                          20
 * Here is the in-order traversal of this tree: [1, 4, 10, 14, 15, 19, 20]
 */
public class InOrderTraversal {

    public static List<Integer> inOrderTraversal(Node node){

        List<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        pushToStack(node,stack);
        while(!stack.isEmpty()){
            Node currentNode = stack.pop();
           result.add(currentNode.value);
           pushToStack(currentNode.right,stack);
        }
        return  result;
    }

    private static void pushToStack(Node node, Stack<Node> stack){
        while(node != null){
            stack.push(node);
            node = node.left;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(4);
        root.left.left = new Node(1);
        root.right = new Node(15);
        root.right.left = new Node(14);
        root.right.right = new Node(19);
        root.right.right.right = new Node(20);
        System.out.println(inOrderTraversal(root));
    }
}
