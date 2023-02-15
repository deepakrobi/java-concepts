package dynamicprogramming.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Problem Statement
 *
 * Given two strings ‘s1’ and ‘s2’, find the length of the longest subsequence which is common in both the strings.
 *
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without
 * changing the order of the remaining elements.
 *
 * Example 1:
 * Input: s1 = "abdca"
 *        s2 = "cbda"
 * Output: 3
 * Explanation: The longest common subsequence is "bda".

 * Example 2:
 * Input: s1 = "passport"
 *        s2 = "ppsspt"
 * Output: 5
 * Explanation: The longest common subsequence is "psspt".
 */
public class LongestCommonSubSequence {

    public static int find(String s1, String s2){
        if((s1 == null && s2 != null) || ( s1!= null && s2 == null)){
            return 0;
        }

        if(s1.equals(s2)){
            return s1.length();
        }

        Integer [] [] cache = new Integer[s1.length()][s2.length()];
       return findRecursive(s1,s2,0,0,cache);
    }

    private static int findRecursive(String s1, String s2, int s1Index, int s2Index, Integer[][] cache) {
        if(s1Index == s1.length() || s2Index == s2.length()){
            return 0;
        }
        // if both characters are same
        if(s1.charAt(s1Index) == s2.charAt(s2Index)){
            return cache[s1Index][s2Index] = 1+ findRecursive(s1,s2,s1Index+1,s2Index+1,cache);
        }

        return cache[s1Index][s2Index] = Math.max(findRecursive(s1,s2,s1Index+1,s2Index,cache),
                findRecursive(s1,s2,s1Index,s2Index+1,cache));
    }

    @Test
    void test1() {
        String s1 = "abdca";
        String s2 = "cbda";
        Assertions.assertEquals(3, find(s1, s2));
    }

    @Test
    void test2() {
        String s1 = "passport";
        String s2 = "ppsspt";
        Assertions.assertEquals(5, find(s1, s2));
    }
}
