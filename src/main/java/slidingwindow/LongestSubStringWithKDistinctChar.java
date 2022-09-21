package slidingwindow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * Problem Statement
 * Given a string, find the length of the longest substring in it with no more than K distinct characters.
 *
 * You can assume that K is less than or equal to the length of the given string.
 *
 * Example 1:
 *
 * Input: String="araaci", K=2
 * Output: 4
 * Explanation: The longest substring with no more than '2' distinct characters is "araa".
 *
 * Example 2:
 * Input: String="araaci", K=1
 * Output: 2
 * Explanation: The longest substring with no more than '1' distinct characters is "aa".
 *
 * Example 3:
 * Input: String="cbbebi", K=3
 * Output: 5
 * Explanation: The longest substrings with no more than '3' distinct characters are "cbbeb" & "bbebi".
 */
public class LongestSubStringWithKDistinctChar {
    public static int findLength(String str, int k) {
        int answer = Integer.MIN_VALUE;
        if (str == null || str.isEmpty() || str.length() < k) {
            throw new IllegalArgumentException("invalid input");
        }

        int windowStart = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            Character currentChar = str.charAt(windowEnd);
            map.put(currentChar, map.getOrDefault(currentChar, 0) + 1);
            while (map.size() > k) {
                Character windowStartChar = str.charAt(windowStart);
                map.put(windowStartChar, map.get(windowStartChar) - 1);
                if (map.get(windowStartChar) == 0) {
                    map.remove(windowStartChar);
                }
                windowStart++;
            }
            answer = Math.max(answer, windowEnd - windowStart + 1);
        }

        return answer;
    }

    @Test
    void test1() {
        Assertions.assertEquals(4, findLength("araaci", 2));
    }

    @Test
    void test2() {
        Assertions.assertEquals(2, findLength("araaci", 1));
    }
    @Test
    void test3() {
        Assertions.assertEquals(5, findLength("cbbebi", 3));
    }
}