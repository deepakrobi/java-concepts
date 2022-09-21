package slidingwindow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem Statement
 *
 * Given a string and a pattern, find out if the string contains any permutation of the pattern.
 *
 * Permutation is defined as the re-arranging of the characters of the string. For example, “abc” has the following six permutations:
 *
 * abc
 * acb
 * bac
 * bca
 * cab
 * cba
 * If a string has ‘n’ distinct characters, it will have n!n! permutations.
 *
 * Example 1:
 *
 * Input: String="oidbcaf", Pattern="abc"
 * Output: true
 * Explanation: The string contains "bca" which is a permutation of the given pattern.
 * Example 2:
 *
 * Input: String="odicf", Pattern="dc"
 * Output: false
 * Explanation: No permutation of the pattern is present in the given string as a substring.
 * Example 3:
 *
 * Input: String="bcdxabcdy", Pattern="bcdyabcdx"
 * Output: true
 * Explanation: Both the string and the pattern are a permutation of each other.
 * Example 4:
 *
 * Input: String="aaacb", Pattern="abc"
 * Output: true
 * Explanation: The string contains "acb" which is a permutation of the given pattern.
 */
public class PermutationsInAString {

    public static boolean findPermutation(String str, String pattern) {
        int windowStart = 0, matched = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : pattern.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 1));
        }

        // our goal is to matched all the characters from the 'map' with the
        // current window try to extend the range [windowStart, windowEnd]

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if (map.containsKey(rightChar)) {
                    map.put(rightChar, map.get(rightChar) - 1);
                    /**You only increment the matched count if map.get(rightChar) ==0. if it is in negatives, it means
                     * there were duplicate chars in strings which are matching the pattern, so no need to increment the
                     * counter.
                     */
                    if (map.get(rightChar) == 0) { // // character is completely matched
                        matched++;
                    }
            }

            if (matched == map.size()) {
                return true;
            }

            if (windowEnd >= pattern.length() - 1) {
                char leftChar = str.charAt(windowStart++);
                if (map.containsKey(leftChar)) {
                    if(map.get(leftChar) ==0) {
                        matched--;// before putting the char back, decrement the matched count
                        // put the character back for matching
                    }
                    map.put(leftChar, map.get(leftChar) + 1);
                }
            }
        }
        return false;
    }

    @Test
    void test1(){
        Assertions.assertTrue(findPermutation("oidbcaf","abc"));
    }

    @Test
    void test2(){
        Assertions.assertFalse(findPermutation("odicf","dc"));
    }

    @Test
    void test3(){
        Assertions.assertTrue(findPermutation("aadbcaf","abc"));
    }

    @Test
    void test4(){
        Assertions.assertTrue(findPermutation("aaacb","abc"));
    }
}
