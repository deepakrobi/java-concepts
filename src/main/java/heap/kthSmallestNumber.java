package heap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Problem Statement
 *
 * Given an unsorted array of numbers, find Kth smallest number in it.
 *
 * Please note that it is the Kth smallest number in the sorted order, not the Kth distinct element.
 *
 * Note: For a detailed discussion about different approaches to solve this problem, take a look at Kth Smallest Number.
 *
 * Example 1:
 *
 * Input: [1, 5, 12, 2, 11, 5], K = 3
 * Output: 5
 * Explanation: The 3rd smallest number is '5', as the first two smaller numbers are [1, 2].
 * Example 2:
 *
 * Input: [1, 5, 12, 2, 11, 5], K = 4
 * Output: 5
 * Explanation: The 4th smallest number is '5', as the first three small numbers are [1, 2, 5].
 * Example 3:
 *
 * Input: [5, 12, 11, -1, 12], K = 3
 * Output: 11
 * Explanation: The 3rd smallest number is '11', as the first two small numbers are [5, -1].
 */
public class kthSmallestNumber {

    public static int findKthSmallestNumber(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((n1, n2) -> n2 - n1);
       // PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Comparator.comparingInt(a->a.intValue()));

        // put first k numbers in the max heap
        for (int i = 0; i < k; i++)
            maxHeap.add(nums[i]);

        // go through the remaining numbers of the array, if the number from the array is
        // smaller than the top (biggest) number of the heap, remove the top number from
        // heap and add the number from array
        for (int i = k; i < nums.length; i++) {
            if (nums[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(nums[i]);
            }
        }

        // the root of the heap has the Kth smallest number
        return maxHeap.peek();
    }

    @Test
    void test1(){
        Assertions.assertEquals(12,findKthSmallestNumber(new int[]{1, 5, 12, 2},4));
    }

    @Test
    void test2() {
        Assertions.assertEquals(5, findKthSmallestNumber(new int[]{1, 5, 12, 2, 11, 7}, 4));
        //3, 1, 5, 12, 2, 11, 6, 13, 4
    }
}
