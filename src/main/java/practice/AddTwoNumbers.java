package practice;
/**You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order,
 and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Input: l1 = [2,4,3], l2 = [5,6,4]
 Output: [7,0,8]
 Explanation: 342 + 465 = 807.

 Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 Output: [8,9,9,9,0,0,0,1]

 Constraints:
 The number of nodes in each linked list is in the range [1, 100].
 0 <= Node.val <= 9
 It is guaranteed that the list represents a number that does not have leading zeros.
 */
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

    @Override
    public String toString() {
        String value = "[" + val + "]";
        ListNode l1 = this;
        while (l1.next != null) {
            l1 = l1.next;
            value += "[" + l1.val + "]";
        }
        return String.format(" [%s] ", value);
    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode resultHead = null;
        int[] sum = new int[]{0, 0};

        while (l1 != null || l2 != null) {
            int value1 = l1 == null ? 0 : l1.val;
            int value2 = l2 == null ? 0 : l2.val;
            if (sum[1] > 0) {
                value1 += sum[1];
            }
            sum = addSumAndGetCarryOver(value1, value2);
            if (result == null) {
                result = new ListNode(sum[0]);
                resultHead = result;
            } else {
                result.next = new ListNode(sum[0]);
                result = result.next;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (sum[1] != 0) {
            // this is for any last carryover
            result.next = new ListNode(sum[0]);
        }
        return resultHead;
    }

    public ListNode addTwoNumberUpdated(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode resultHead = null;
        int carryOver = 0;
        while (l1 != null || l2 != null) {
            int value1 = l1 == null ? 0 : l1.val;
            int value2 = l2 == null ? 0 : l2.val;
            int sum = value1 + value2 + carryOver;
            int nodeValue = sum % 10;
            carryOver = sum / 10;
            if (result == null) {
                result = new ListNode(nodeValue);
                resultHead = result;
            } else {
                result.next = new ListNode(nodeValue);
                result = result.next;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        if (carryOver > 0) {
            result.next = new ListNode(carryOver);
        }
        return resultHead;
    }
    private int[] addSumAndGetCarryOver(int v1, int v2) {
        int[] sum = new int[]{0, 0};
        if (v1 + v2 < 10) {
            sum[0] = v1 + v2;
            sum[1] = 0;
        } else if (v1 + v2 == 10) {
            sum[0] = 0;
            sum[1] = 1;
        } else {
            sum[0] = (v1 + v2) - 10;
            sum[1] = 1;
        }
        return sum;
    }
}

public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(9, new ListNode(9, new ListNode(9)));
        ListNode l2 = new ListNode(9, new ListNode(9));
        Solution solution = new Solution();
        System.out.println(solution.addTwoNumbers(l1, l2));
        System.out.println();
        System.out.println(solution.addTwoNumberUpdated(l1, l2));
    }
}
