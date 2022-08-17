package PalindromeLinkedList;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }

        String str = new String();
        while (head.next != null) {
            str += head.val;
            head = head.next;
        }
        if (head != null) {
            str += head.val;
        }
        System.out.println(" str : " + str);
        return (isPalindrome(str));
    }

    private String reverseString(String input) {
        if (input == null) return input;
        char[] inputChar = input.toCharArray();
        String returnStr = new String();
        for (int i = inputChar.length - 1; i >= 0; i--) {
            returnStr += inputChar[i];

        }
        return returnStr;
    }

    private boolean isPalindrome(String input) {
        if (input == null) return false;
        int i = 0;
        int j = input.length() - 1;
        while (i < j) {
            if (input.charAt(i) != input.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}

public class Palindrome {
    public static void main(String[] args) {

        //ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        ListNode listNode = new ListNode(1,new ListNode(2,new ListNode(1)));
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(listNode));
    }
}

