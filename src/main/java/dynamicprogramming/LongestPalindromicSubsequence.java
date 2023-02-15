package dynamicprogramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given a string s, find the longest palindromic subsequence's length in s.
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
 *
 * Example 1:
 *
 * Input: s = "bbbab"
 * Output: 4
 * Explanation: One possible longest palindromic subsequence is "bbbb".
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: 2
 * Explanation: One possible longest palindromic subsequence is "bb".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists only of lowercase English letters.
 */
public class LongestPalindromicSubsequence {
    Integer [][] cache;

    public int longestPalindromeSubseq(String s) {
        if(s.isEmpty()){
            return 0;
        }
        cache = new Integer[s.length()][s.length()];
        return  longestPalindromeSubseq(s.toCharArray(),0,s.length()-1);
    }

    public int longestPalindromeSubseq(char[] sequence, int i, int j) {
        if (cache[i][j] != null) {
            return cache[i][j];
        }
        // Base Case 1: If there is only 1 character
        if (i == j) {
            return cache[i][j] = 1;
        }
        // Base Case 2 : If there is only 2 character and both are equal
        if (i + 1 == j && sequence[i] == sequence[j]) {
            return cache[i][j] = 2;
        }

        // if the first and last character match.
        if (sequence[i] == sequence[j]) {
            return cache[i][j] = longestPalindromeSubseq(sequence, i + 1, j - 1) +2;
        }
        // if the first and last character doesn't match
        return cache[i][j] = Math.max(longestPalindromeSubseq(sequence, i + 1, j), longestPalindromeSubseq(sequence, i, j - 1));
    }

    @Test
    void test1() {
      Assertions.assertEquals(3,new LongestPalindromicSubsequence().longestPalindromeSubseq("bbb"));
        Assertions.assertEquals(0, new LongestPalindromicSubsequence().longestPalindromeSubseq(""));
        Assertions.assertEquals(3, new LongestPalindromicSubsequence().longestPalindromeSubseq("abab"));
        Assertions.assertEquals(4, new LongestPalindromicSubsequence().longestPalindromeSubseq("bbbab"));
        Assertions.assertEquals(2, new LongestPalindromicSubsequence().longestPalindromeSubseq("cbbd"));

    }

    @Test
    void testfailed() {
        Assertions.assertEquals(3,new LongestPalindromicSubsequence().longestPalindromeSubseq("cddpd"));
    }
}
