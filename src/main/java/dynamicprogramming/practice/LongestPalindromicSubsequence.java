package dynamicprogramming.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given a sequence, find the length of its Longest Palindromic Subsequence (LPS). In a palindromic subsequence,
 * elements read the same backward and forward.
 *
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without
 * changing the order of the remaining elements.
 *
 * Example 1:
 * Input: "abdbca"
 * Output: 5
 * Explanation: LPS is "abdba".
 *
 * Example 2:
 * Input: = "cddpd"
 * Output: 3
 * Explanation: LPS is "ddd".
 *
 * Example 3:
 * Input: = "pqr"
 * Output: 1
 * Explanation: LPS could be "p", "q" or "r".
 */
public class LongestPalindromicSubsequence {

    public static int find(String str){
        Integer [] [] cache = new Integer[str.length()][str.length()];
        return findRecursive(str.toCharArray(), 0, str.length() - 1, cache);
    }

    private static int findRecursive(char[] input, int start, int end, Integer [] [] cache) {
        // if there is only 1 character
        if (start == end) {
            return 1;
        }
        // if there are 2 characters and they are equal i.e. they are palindrome
        if (start + 1 == end && input[start] == input[end]) {
            return 2;
        }

        if (cache[start][end] != null) {
            return cache[start][end];
        }

        if (input[start] == input[end]) {
            // if first and last character matches
            return cache[start][end] = findRecursive(input, start + 1, end - 1, cache) + 2;
        }

        // if first and last character doesn't matches
        return cache[start][end] = Math.max(findRecursive(input, start, end - 1, cache), findRecursive(input, start + 1, end, cache));
    }

    @Test
    void test1(){
        Assertions.assertEquals(5,find("abdbca"));
    }

    @Test
    void test2(){
        Assertions.assertEquals(3,find("cddpd"));
    }

    @Test
    void test3(){
        Assertions.assertEquals(1,find("pqr"));
    }
}
