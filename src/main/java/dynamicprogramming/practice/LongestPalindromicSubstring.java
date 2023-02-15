package dynamicprogramming.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongestPalindromicSubstring {

    public static int find(String str){
        Integer[] [] cache = new Integer[str.length()][str.length()];

        return findRecursive(str.toCharArray(),0,str.length()-1,cache);
    }
    private static int findRecursive(char[] str, int start, int end,Integer[][] cache) {

        // if there is only 1 character
        if (start == end) {
            return 1;
        }
        // if there are 2 character and they are equal i.e. palindrome
        if (start + 1 == end && str[start] == str[end]) {
            return 2;
        }

        if (cache[start][end] != null) {
            return cache[start][end];
        }
        // if first and last character are equal
        if (str[start] == str[end]) {
            String palindrome = new String(str, start, end - start + 1);
            String remainingString = new String(str, start + 1, palindrome.length() - 2);
            // check if remaining string is also a palindrome

            if (remainingString.length() == findRecursive(str, start + 1, end - 1,cache)) {
                return cache[start][end] = palindrome.length();
            }
        }

        // if first and last character are not equal

        return cache[start][end] = Math.max(findRecursive(str, start, end - 1,cache), findRecursive(str, start + 1, end,cache));
    }

    public static int count(String str) {
        Integer[][] cache = new Integer[str.length()][str.length()];
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                if (isPalindrome(str.substring(i, j + 1))) {
                    System.out.println(str.substring(i, j + 1));
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isPalindrome(String str) {
        if (str.isEmpty()) {
            return false;
        }
        if (str.length() < 2) {
            return true;
        }
        int start = 0;
        int end = str.length() - 1;
        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    @Test
    void test1(){
        Assertions.assertEquals(3,find("abdbca"));
    }


    @Test
    void test2(){
        Assertions.assertEquals(3,find("cddpd"));
    }

    @Test
    void test3(){
        Assertions.assertEquals(1,find("pqr"));
    }

    @Test
    void test4(){
        Assertions.assertEquals(7,count("abdbca"));
    }

    @Test
    void test5(){
        Assertions.assertEquals(7,count("cddpd"));
    }

    @Test
    void test6(){
        Assertions.assertEquals(3,count("pqr"));
    }

    @Test
    void test7(){
        Assertions.assertEquals(4,count("aba"));
    }
}
