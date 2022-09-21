package slidingwindow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**Given an array of positive numbers and a positive number ‘k,’ find the maximum sum of any contiguous subarray of size ‘k’.

 Example 1:
 Input: [2, 1, 5, 1, 3, 2], k=3
 Output: 9
 Explanation: Subarray with maximum sum is [5, 1, 3].

 Example 2:
 Input: [2, 3, 4, 1, 5], k=2
 Output: 7
 Explanation: Subarray with maximum sum is [3, 4].

 */
public class MaxSumOfSubArray {
    public static int findMaxSumSubArray(int k, int[] arr) {
        int maxSum = 0;
        int windowStart = 0;
        int windowSum = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd];
            if (windowEnd - windowStart == k - 1) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= arr[windowStart];
                windowStart++;
            }
        }
        return maxSum;
    }

    @Test
    void test1() {
        Assertions.assertEquals(9, findMaxSumSubArray(3, new int[]{2, 1, 5, 1, 3, 2}));
    }

    @Test
    void test2() {
        Assertions.assertEquals(7, findMaxSumSubArray(2, new int[]{2, 3, 4, 1, 5}));
    }
}
