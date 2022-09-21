package dynamicprogramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the
 * order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 *
 * Example 1:
 *
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * Example 2:
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 *
 *  Example 3:
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 * Constraints:
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
public class LongestIncreasingSubsequence {
    Integer[][] cache;

    public int lengthOfLIS(int[] nums) {
        cache = new Integer[nums.length][nums.length];
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int answer = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    answer = Math.max(answer, dp[i]);
                }
            }
        }
        return answer;
    }

    @Test
    void test7() {
        int[] nums = {0, 1, 0, 3, 2};
        Assertions.assertEquals(3, new LongestIncreasingSubsequence().lengthOfLIS(nums));
    }

    @Test
    void test6() {
        int[] nums = {4, 10, 4, 3, 8, 9}; //3,8,9
        Assertions.assertEquals(3, new LongestIncreasingSubsequence().lengthOfLIS(nums));
    }

    @Test
    void test5() {
        int[] nums = {10, 9, 101, 18}; // 10,101
        Assertions.assertEquals(2, new LongestIncreasingSubsequence().lengthOfLIS(nums));
    }

    @Test
    void test1() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18}; // 2,3,7,101
        Assertions.assertEquals(4, new LongestIncreasingSubsequence().lengthOfLIS(nums));
    }

    @Test
    void test2() {
        int[] nums = {0, 1, 0, 3, 2, 3};
        Assertions.assertEquals(4, new LongestIncreasingSubsequence().lengthOfLIS(nums));
    }

    @Test
    void test3() {
        int[] nums = {7, 7, 7, 7, 7, 7, 7};
        Assertions.assertEquals(1, new LongestIncreasingSubsequence().lengthOfLIS(nums));
    }

    @Test
    void test4() {
        int[] nums = {-2, -1};
        Assertions.assertEquals(2, new LongestIncreasingSubsequence().lengthOfLIS(nums));
    }
}
