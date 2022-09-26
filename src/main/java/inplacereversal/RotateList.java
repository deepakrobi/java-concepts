package inplacereversal;

class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}

public class RotateList {

    public static Node rotate(Node head, int rotations) {
        if (head == null || head.next == null || rotations == 0) {
            return head;
        }

        Node lastNode = head;
        int length = 1;
        while (lastNode.next != null) {
            length++;
            lastNode = lastNode.next;
        }

        rotations %= length; //no need to do rotations more than lenght of list.
        lastNode.next = head; // create circular dependency
        int skipLength = length - rotations;

        Node lastRotatedNode = head;
        for (int i = 0; i < skipLength -1 ; i++) {
            lastRotatedNode = lastRotatedNode.next;
        }

        head = lastRotatedNode.next;
        lastRotatedNode.next = null;

        return head;
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

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        System.out.print("Linked List: ");
        printLinkedList(head);
        System.out.println();
        System.out.print("Rotated Linked List: ");
        printLinkedList(rotate(head,8));
    }
}
