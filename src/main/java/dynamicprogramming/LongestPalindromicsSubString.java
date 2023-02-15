package dynamicprogramming;

import com.google.common.base.Stopwatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Given a string s, return the longest palindromic substring in s.
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 *
 * Input: s = "cbbd"
 * Output: "bb"
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 */

public class LongestPalindromicsSubString {
    String [][] cache;

    public String longestPalindrome(String str) {
        cache = new String [str.length()][str.length()];
        return longestPalindrome(str.toCharArray(),0,str.length()-1);
    }

    private String longestPalindrome(char [] substring, int i, int j) {
        if(cache[i][j] != null){
            return cache[i][j];
        }
        if (i > j)
            return cache [i][j] = "";

        // Base case 1: if there is only 1 character
        if (i == j) {
            return cache [i][j] = String.valueOf(substring[i]);
        }

        // Base case 2: if there are 2 character, and they are equals.
        if (i + 1 == j && substring[i] == substring[j]) {
            return cache [i][j] = new String(substring, i, 2);
        }

        // if first and last character matches
        if (substring[i] == substring[j]) {
            String palindrome = new String(substring, i, j - i + 1);
            String remainingString = new String(substring, i + 1, palindrome.length() - 2);
            if (remainingString.equals(longestPalindrome(substring, i + 1, j - 1))) {
                return cache[i][j] = palindrome;
            }
        }

        String sub1 = longestPalindrome(substring, i, j - 1);
        String sub2 = longestPalindrome(substring, i + 1, j);
        return cache [i][j] = sub1.length() > sub2.length() ? sub1 : sub2;
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println(new LongestPalindromicsSubString().longestPalindrome("abbcccbbbcaaccbababcbcabca"));
        System.out.println( "Total time taken : "+ stopwatch.elapsed(TimeUnit.MILLISECONDS));

    }

    @Test
    void test1() {
        Assertions.assertEquals("bb", longestPalindrome("abb"));
        // Assertions.assertEquals("bab",longestPalindrome("babad"));

        String expectedReturn[] = {"bab", "aba"};
        List<String> expectedTitlesList = Arrays.asList(expectedReturn);
        Assertions.assertTrue(expectedTitlesList.contains((longestPalindrome("babad"))));
        Assertions.assertEquals("bb", longestPalindrome("cbbd"));
        Assertions.assertEquals("aca",longestPalindrome("aacabdkacaa"));
    }

    @Test
    void testFailed(){
        Assertions.assertEquals("aca",longestPalindrome("aacabdkacaa"));
    }
    @Test
    void test2(){
        Assertions.assertEquals("bdb",longestPalindrome("abdbca"));
    }

    @Test
    void charStringconversion(){
        String str = "abcdefgha";
    System.out.println(new String(str.toCharArray(),0,8+1));
        System.out.println(new String(str.toCharArray(),1,str.length()-2));

    }
}
