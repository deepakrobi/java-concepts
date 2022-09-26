package inplacereversal;

class Node {
    int value = 0;
    Node next;

    Node(int value) {
        this.value = value;
    }
}

public class ReverseLinkedList {

    public static Node reverse(Node head) {
        if( head == null || head.next== null){
            return head ;
        }

        Node current = head;
        Node previous = null;
        Node next = null;
        while(current != null){
            next = current.next;
            current.next = previous; // reverse the list
            previous = current;
            current = next; // move on to next node.
        }
        return previous; // previous will be new head
    }

    private static void printLinkedList( Node head){
        System.out.println();
        while(head != null){
            if(head.next == null){
                System.out.print(head.value);
            }else {
                System.out.print(head.value + " -> ");
            }
            head = head.next;
        }
    }
    public static void main(String[] args) {
        Node head = new Node(2);
        head.next = new Node(4);
        head.next.next = new Node(6);
        head.next.next.next = new Node(8);
        head.next.next.next.next = new Node(10);
        System.out.print("Linked List: ");
        printLinkedList(head);
        System.out.println();
        System.out.print("Reversed Linked List: ");
        printLinkedList(reverse(head));
    }
}
