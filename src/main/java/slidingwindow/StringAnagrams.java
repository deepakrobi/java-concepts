package slidingwindow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem Statement
 * Given a string and a pattern, find all anagrams of the pattern in the given string.
 * Every anagram is a permutation of a string.
 * As we know, when we are not allowed to repeat characters while finding permutations of a string, we get N!N! permutations
 * (or anagrams) of a string having NN characters. For example, here are the six anagrams of the string “abc”:
 *
 * abc
 * acb
 * bac
 * bca
 * cab
 * cba
 * Write a function to return a list of starting indices of the anagrams of the pattern in the given string.
 *
 * Example 1:
 * Input: String="ppqp", Pattern="pq"
 * Output: [1, 2]
 * Explanation: The two anagrams of the pattern in the given string are "pq" and "qp".
 *
 * Example 2:
 * Input: String="abbcabc", Pattern="abc"
 * Output: [2, 3, 4]
 * Explanation: The three anagrams of the pattern in the given string are "bca", "cab", and "abc".
 */
public class StringAnagrams {

    public static List<Integer> find(String str, String pattern) {
        List<Integer> result = new ArrayList<>();
        int windowStart = 0, matched = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : pattern.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);

            if (map.containsKey(rightChar)) {
                map.put(rightChar, map.get(rightChar) - 1);
                if (map.get(rightChar) == 0) {
                    matched++;
                }
            }

            if (matched == map.size()) {
                result.add(windowStart);
            }

            if (windowEnd >= pattern.length() - 1) {
                char leftChar = str.charAt(windowStart);
                if (map.containsKey(leftChar)) {
                    if (map.get(leftChar) == 0) {
                        matched--;
                    }
                    map.put(leftChar, map.get(leftChar) + 1);
                }
                windowStart++;
            }
        }
        return result;
    }

    @Test
    void test1(){
        ArrayList<Integer> result =new ArrayList<>(Arrays.asList(1,2));
        String str = "ppqp";
        String pattern = "pq";
        Assertions.assertEquals(result,find(str,pattern));
    }

    @Test
    void test2(){
        ArrayList<Integer> result =new ArrayList<>(Arrays.asList(2,3,4));
        String str = "abbcabc";
        String pattern = "abc";
        Assertions.assertEquals(result,find(str,pattern));
    }
}
