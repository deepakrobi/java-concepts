package slidingwindow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * Problem Statement

 Given a string, find the length of the longest substring, which has all distinct characters.

 Example 1:
 Input: String="aabccbb"
 Output: 3
 Explanation: The longest substring with distinct characters is "abc".

 Example 2:
 Input: String="abbbb"
 Output: 2
 Explanation: The longest substring with distinct characters is "ab".

 Example 3:
 Input: String="abccde"
 Output: 3
 Explanation: Longest substrings with distinct characters are "abc" & "cde".

 */
public class LongestSubstringWithDistinctChars {

    public static int findLength(String str) {
        int windowStart = 0;
        int answer = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);
            while (map.get(rightChar) > 1) {
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
        Assertions.assertEquals(3, findLength("aabccbb"));
    }

    @Test
    void test2() {
        Assertions.assertEquals(2, findLength("abbbb"));
    }

    @Test
    void test3() {
        Assertions.assertEquals(3, findLength("abccde"));
    }
}
