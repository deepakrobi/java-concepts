package practice;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * Example 1:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 *
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 *
 * Input: head = [1,2], n = 1
 * Output: [1]
 */

public class RemoveGIvenNode {
    public static ListNode removeNthFromEnd(ListNode head, int n) {

        Map<Integer, ListNode> map = new HashMap<>();
        ListNode resultHead = head;
        int length = 0;
        while (head.next != null) {
            map.put(length, head);
            head = head.next;
            length++;
        }
        map.put(length,head);
        length++;

        head = resultHead;
        ListNode nodeToBeRemoved = map.get(length - n);
        if(head == nodeToBeRemoved){
            resultHead = head.next;
            return resultHead;
        }
        while (head.next != null) {
            if (head.next == nodeToBeRemoved) {
                head.next = head.next.next;
                break;
            }
            head = head.next;
        }

        return resultHead;
    }

    public static void main (String [] args){
        ListNode head = new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))));
        ListNode result  = removeNthFromEnd(head,2);
        printNodes(result);

        System.out.println();
        head = new ListNode(1,new ListNode(2));
        result  = removeNthFromEnd(head,1);
        printNodes(result);

        System.out.println();
        head = new ListNode(1);
        result  = removeNthFromEnd(head,1);
        printNodes(result);
    }

    public static void printNodes(ListNode head){
        if(head == null){
            System.out.println( " ");
            return;
        }
        System.out.print(head.val);
        while(head.next != null){
            System.out.print(" " +head.next.val);
            head = head.next;
        }
    }
}
