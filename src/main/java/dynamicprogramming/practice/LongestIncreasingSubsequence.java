package dynamicprogramming.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
Problem Statement

Given a number sequence, find the length of its Longest Increasing Subsequence (LIS). In an increasing subsequence, all the elements are in increasing order (from lowest to highest).

Example 1:

Input: {4,2,3,6,10,1,12}
Output: 5
Explanation: The LIS is {2,3,6,10,12}.
Example 1:

Input: {-4,10,3,7,15}
Output: 4
Explanation: The LIS is {-4,3,7,15}.
 */
public class LongestIncreasingSubsequence {

    public static int find(int[] input) {
        Integer[][] cache = new Integer[input.length][input.length + 1];
        if (input == null || input.length < 1) {
            return 0;
        }
        return findRecursive(input, 0, -1, cache);
    }

    public static int findRecursive(int[] input, int current, int previous, Integer[][] cache ) {
        if (current == input.length) {
            return 0;
        }

        if (cache[current][previous + 1] != null) {
            return cache[current][previous + 1];
        }
        // include input[currentIndex] if it is larger than the last included number
        int c1 = 0;

        if (previous == -1 || input[current] > input[previous]) {
            c1 = 1 + findRecursive(input, current + 1, current, cache);
        }

        // excluding the number at currentIndex
        int c2 = findRecursive(input, current + 1, previous, cache);
        return cache[current][previous + 1] = Math.max(c1, c2);
    }

    @Test
    void test1() {
        Assertions.assertEquals(5, find(new int[]{4,2,3,6,10,1,12}));
    }

    @Test
    void test2() {
        Assertions.assertEquals(4, find(new int[]{-4, 10, 3, 7, 15}));
    }
}
