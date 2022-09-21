package slidingwindow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem Statement
 *
 * Given a string with lowercase letters only, if you are allowed to replace no more than ‘k’ letters with any letter,
 * find the length of the longest substring having the same letters after replacement.
 *
 * Example 1:
 *
 * Input: String="aabccbb", k=2
 * Output: 5
 * Explanation: Replace the two 'c' with 'b' to have a longest repeating substring "bbbbb".
 * Example 2:
 *
 * Input: String="abbcb", k=1
 * Output: 4
 * Explanation: Replace the 'c' with 'b' to have a longest repeating substring "bbbb".
 * Example 3:
 *
 * Input: String="abccde", k=1
 * Output: 3
 * Explanation: Replace the 'b' or 'd' with 'c' to have the longest repeating substring "ccc".
 */
public class LongestSubstringWithSameLetters {

    public static int findLength(String str, int k) {
        int answer = Integer.MIN_VALUE, windowStart = 0, maxOccurence = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);
            maxOccurence = Math.max(maxOccurence, map.get(rightChar));

            // If total length of window ( minus  maxoccurece of character) is greater than k ( # of letters allowed to be replaced).
            // then we can shrink the window (windowStart++) so it reamins less than or euqal to k
            if (windowEnd - windowStart + 1 - maxOccurence > k) {
                char leftChar = str.charAt(windowStart);
                map.put(leftChar, map.get(leftChar) - 1);
                windowStart++;
            }
            answer = Math.max(answer, windowEnd - windowStart + 1);
        }

        return answer;
    }


    @Test
    void test1() {
        Assertions.assertEquals(5, findLength("aabccbb", 2));
    }

    @Test
    void test2() {
        Assertions.assertEquals(4, findLength("abbcb", 1));
    }

    @Test
    void test3() {
        Assertions.assertEquals(3, findLength("abccde", 1));
    }
}
