package dynamicprogramming.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
/**
Problem Statement

Given two strings ‘s1’ and ‘s2’, find the length of the longest substring which is common in both the strings.

Example 1:

Input: s1 = "abdca"
       s2 = "cbda"
Output: 2
Explanation: The longest common substring is "bd".
Example 2:

Input: s1 = "passport"
       s2 = "ppsspt"
Output: 3
Explanation: The longest common substring is "ssp".

 */
public class LongestCommonSubstring {

    public static int find(String s1, String s2){
        if((s1 == null && s2 != null) || (s2 == null && s1 != null)){
            return 0;
        }
        if(s1.equals(s2)){
            return s1.length();
        }

        int maxCount = Math.min(s1.length(), s2.length());
        Integer [] [] [] cache = new Integer[s1.length()][s2.length()][maxCount];

       return findRecursive(s1,s2,0,0,0,cache);
       // return findLCSLengthRecursive(s1,s2,0,0,0);
    }

    private static int findRecursive(String s1, String s2, int s1Index, int s2Index, int count, Integer[][] [] cache){
        if(s1Index == s1.length() || s2Index ==s2.length()){
            return count;
        }
        if (cache[s1Index][s2Index][count] != null) {
            return cache[s1Index][s2Index][count];
        }
        int c1 = count;
        // charaters are same for s1 and s2.
        if (s1.charAt(s1Index) == s2.charAt(s2Index)) {
            c1 =  findRecursive(s1,s2,s1Index+1,s2Index+1,count+1,cache);
        }
        // characters are not same for s1 and s2.

        int c2= findRecursive(s1,s2,s1Index+1,s2Index,0,cache);
        int c3 = findRecursive(s1,s2,s1Index,s2Index+1,0,cache);
        int max = Math.max(c1,Math.max(c2,c3));

        return cache[s1Index][s2Index][count]= max;

    }

    private static int findLCSLengthRecursive(String s1, String s2, int i1, int i2, int count) {
        if(i1 == s1.length() || i2 == s2.length())
            return count;

        if(s1.charAt(i1) == s2.charAt(i2))
            count = findLCSLengthRecursive(s1, s2, i1+1, i2+1, count+1);

        int c1 = findLCSLengthRecursive(s1, s2, i1, i2+1, 0);
        int c2 = findLCSLengthRecursive(s1, s2, i1+1, i2, 0);

        return Math.max(count, Math.max(c1, c2));
    }

    @Test
    void test1(){
        String s1 = "bdca";
        String s2 = "bda";
        Assertions.assertEquals(2,find(s1,s2));
    }

    @Test
    void test2(){
        String s1 = "abdca";
        String s2 = "cbda";
        Assertions.assertEquals(2,find(s1,s2));
    }

    @Test
    void test3(){
        String s1 = "bda";
        String s2 = "ba";
        Assertions.assertEquals(1,find(s1,s2));
    }


}
