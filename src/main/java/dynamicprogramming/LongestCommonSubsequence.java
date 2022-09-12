package dynamicprogramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted
 * without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * Example 1:
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 *
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 * Constraints:
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 */
public class LongestCommonSubsequence {
    private Integer[][] cache;
    String text1;
    String text2;

    public int longestCommonSubsequence(String text1, String text2) {
        cache = new Integer[text1.length()][text2.length()];
        this.text1 = text1;
        this.text2 = text2;
        return longestCommonSubsequence(0, 0);
    }

    private int longestCommonSubsequence(int text1Index, int text2Index) {
        int longestLength;
        if (text1Index == text1.length() || text2Index == text2.length()) {
            return 0;
        }

        if (cache[text1Index][text2Index] == null) {
            if (text1.charAt(text1Index) == text2.charAt(text2Index)) {
                longestLength = 1 + longestCommonSubsequence(text1Index + 1, text2Index + 1);
            } else {
                longestLength = Math.max(longestCommonSubsequence(text1Index, text2Index + 1),
                        longestCommonSubsequence(text1Index + 1, text2Index));
            }
            cache[text1Index][text2Index] = longestLength;
        }

        return cache[text1Index][text2Index];
    }

    public int dpSingleArray(String text1, String text2) {
        int t1L = text1.length();
        int t2L = text2.length();
        if (t1L == 0 || t2L == 0)
            return 0;
        if (t2L < t1L)
            return dpSingleArray(text2, text1);
        int[] dp = new int[t1L + 1];
        for (int t2 = 1; t2 < t2L + 1; t2++) {
            int prevBest = 0;
            for (int t1 = 1; t1 < t1L + 1; t1++) {
                int temp = dp[t1];
                dp[t1] = (text1.charAt(t1 - 1) == text2.charAt(t2 - 1)) ? 1 + prevBest : Math.max(dp[t1], dp[t1 - 1]);
                prevBest = temp;
            }
        }
        return dp[t1L];
    }

    public int longestCommonSubsequenceBottomUp(String text1, String text2) {
        int[][] result = new int[text1.length() + 1][text2.length() + 1];
        this.text1 = text1;
        this.text2 = text2;

        for (int row = text1.length() -1 ; row >=0; row--) {
            for (int col= text2.length() -1 ; col >=0; col--) {
                if (text1.charAt(row) == text2.charAt(col)) {
                    result[row][col] = 1 + result[row + 1][col + 1];
                } else {
                    result[row][col] = Math.max(result[row + 1][col], result[row][col + 1]);
                }
            }
        }
        return result[0][0];

    }

    @Test
    void test1() {
        LongestCommonSubsequence subsequence = new LongestCommonSubsequence();
        Assertions.assertEquals(3, subsequence.longestCommonSubsequence("abc", "abc"));
        Assertions.assertEquals(3, subsequence.longestCommonSubsequence("abcde", "ace"));
        Assertions.assertEquals(0, subsequence.longestCommonSubsequence("abc", "def"));
    }

    @Test
    void testBottomUp() {
        LongestCommonSubsequence subsequence = new LongestCommonSubsequence();
        Assertions.assertEquals(3, subsequence.longestCommonSubsequenceBottomUp("abc", "abc"));
        Assertions.assertEquals(3, subsequence.longestCommonSubsequenceBottomUp("abcde", "ace"));
        Assertions.assertEquals(0, subsequence.longestCommonSubsequenceBottomUp("abc", "def"));
    }
}
