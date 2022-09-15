package dynamicprogramming;

import LRU.Cache;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence
 * of one or more dictionary words. Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *
 *
 * Constraints:
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 */
public class WordBreak {
Boolean [] cache;
    public boolean wordBreak(String s, List<String> wordDict) {
        cache = new Boolean[s.length()];
        if (s.isEmpty()) {
            return true;
        }
        return wordBreakHelper(s,wordDict,0);
       // return  wordBreakRecur(s,wordDict,0);
    }

    public boolean wordBreakHelper(String str, List<String> wordDict, int start) {
        if (start >= str.length()) {
            return true;
        }
        if(cache[start]!= null){
            return cache[start];
        }
        for (int end = start + 1; end <= str.length(); end++) {
            String subStr = str.substring(start, end);
            if (wordDict.contains(subStr) && wordBreakHelper(str, wordDict, end)) {
                    return cache[start] = true;
            }
        }
        return cache[start] = false;
    }

    private boolean wordBreakRecur(String s, List<String> wordDict, int start) {
        if (start == s.length()) {
            return true;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && wordBreakRecur(s, wordDict, end)) {
                return true;
            }
        }
        return false;
    }

   @Test
    void test1(){
       List<String> wordDict = Arrays.asList("leet","code");
       String str =  "leetcode";
       Assertions.assertTrue(new WordBreak().wordBreak(str,wordDict));
   }

    @Test
    void test2(){
        List<String> wordDict = Arrays.asList("apple","pen");
        String str =  "applepenapple";
        Assertions.assertTrue(new WordBreak().wordBreak(str,wordDict));
    }

    @Test
    void test3(){
        List<String> wordDict = Arrays.asList("cats","dog","sand","and","cat");
        String str =  "catsandog";
        Assertions.assertFalse(new WordBreak().wordBreak(str,wordDict));
    }

    @Test
    void test4(){
        List<String> wordDict = Arrays.asList("car","ca","rs");
        String str =  "cars";
        Assertions.assertTrue(new WordBreak().wordBreak(str,wordDict));
    }

    @Test
    void test5(){
        List<String> wordDict = Arrays.asList("a","b","bbb","bbbb");
        String str =  "bb";
        Assertions.assertTrue(new WordBreak().wordBreak(str,wordDict));
    }

    @Test
    void test6(){
        String str =  "ccbb";
        List<String> wordDict = Arrays.asList("bc","cb");

        Assertions.assertFalse(new WordBreak().wordBreak(str,wordDict));
    }
}
