package slidingwindow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Problem Statement
 *
 * Given an array containing 0s and 1s, if you are allowed to replace no more than ‘k’ 0s with 1s, find the length of the longest contiguous subarray having all 1s.
 *
 * Example 1:
 *
 * Input: Array=[0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1], k=2
 * Output: 6
 * Explanation: Replace the '0' at index 5 and 8 to have the longest contiguous subarray of 1s having length 6.
 * Example 2:
 *
 * Input: Array=[0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1], k=3
 * Output: 9
 * Explanation: Replace the '0' at index 6, 9, and 10 to have the longest contiguous subarray of 1s having length 9.
 */
public class LongestSubArrayWithOnesAfterReplacement {
    public static int findLength(int[] arr, int k){
        int answer = 0, windowStart = 0, maxOnes = 0;

        for(int windowEnd = 0; windowEnd<arr.length;windowEnd++) {
            if (arr[windowEnd] == 1) {
                maxOnes++;
            }
            if (windowEnd - windowStart + 1 - maxOnes > k) {
                if (arr[windowStart] == 1) {
                    maxOnes--;
                }
                windowStart++;
            }
            answer = Math.max(answer, windowEnd - windowStart + 1);
        }

        return answer;
    }

    @Test
    void test1(){
        Assertions.assertEquals(6,findLength(new int[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1},2));
    }

    @Test
    void test2(){
        Assertions.assertEquals(9,findLength(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1},3));
    }
}
