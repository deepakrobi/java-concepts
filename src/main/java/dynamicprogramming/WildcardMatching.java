package dynamicprogramming;

import com.google.common.base.Stopwatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import recursion.MyCounter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public  class WildcardMatching {

    public static boolean isMatch(String s, String p) {
        MyCounter counter = new MyCounter(0);
        boolean match = isMatch(s,p,counter);
        System.out.print("Count : " +counter.getCounter());
        System.out.println();
        return match;
    }
    public static boolean isMatch(String s, String p,MyCounter count) {
        count.increment();
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        if (p.equals("*") || p.equals(s)) {
            return true;
        }
        boolean match = (!s.isEmpty() &&
                (p.charAt(0) == '?' || p.charAt(0) == '*' || p.charAt(0) == s.charAt(0)));

        if (p.charAt(0) == '*') {
            p = udpatePattern(p);
            return isMatch(s, p.substring(1),count)
                    || match && isMatch(s.substring(1), p,count);
        } else {
            return match && isMatch(s.substring(1), p.substring(1),count);
        }
    }

    public static boolean isMatchDP(String s, String p) {
        MyCounter counter = new MyCounter(0);
        boolean match = isMatchRecursiveDP(s, p, new HashMap<>(),counter);
        System.out.print("Count : " +counter.getCounter());
        System.out.println();
        return  match;
    }


    public static boolean isMatchRecursiveDP(String s, String p, Map<String , Boolean> map, MyCounter count) {
        count.increment();
        String key = s + ":" + p;
        boolean answer = false;
        if (p.isEmpty()) {
            map.put(key, s.isEmpty());
            return s.isEmpty();
        }

        if (p.equals("*") || p.equals(s)) {
            map.put(key, true);
            return true;
        }

        if (map.get(key) != null) {
            return map.get(key);
        }

        boolean match = (!s.isEmpty() && (p.charAt(0) == '?' || p.charAt(0) == '*' || p.charAt(0) == s.charAt(0)));

        if (p.charAt(0) == '*') {
            p = udpatePattern(p);
            answer = isMatchRecursiveDP(s, p.substring(1), map,count) || match && isMatchRecursiveDP(s.substring(1), p, map,count);
        } else {
            answer = match && isMatchRecursiveDP(s.substring(1), p.substring(1), map,count);
        }
        map.put(key, answer);

        return answer;
    }


    static String udpatePattern(String pattern) {
        while (!pattern.isEmpty() && pattern.charAt(0) == '*') {
            pattern = pattern.substring(1);
        }
        return "*" + pattern;
    }

    public boolean isMatchMemorization(String str, String  pattern) {
        Boolean [] [] dp = new Boolean[pattern.length()+1][str.length()+1];
        return   isMatchMemorization(str,pattern,0,0,dp);
    }

    public boolean isMatchMemorization(String str, String pattern, int patternIndex, int strIndex, Boolean[][] dp) {
        boolean answer = false;
        if (dp[patternIndex][strIndex] != null) {
            return dp[patternIndex][strIndex];
        }
        if (patternIndex == pattern.length()) {
            answer = strIndex == str.length();
        } else if (pattern.equals("*") || pattern.equals(str)) {
            answer = true;
        } else {
            boolean firstCharMatch = (strIndex < str.length() && (pattern.charAt(patternIndex) == '?' || pattern.charAt(patternIndex) == '*'
                    || pattern.charAt(patternIndex) == str.charAt(strIndex)));
            if (pattern.charAt(patternIndex) == '*') {
                answer = isMatchMemorization(str, pattern, patternIndex + 1, strIndex, dp);
                if (!answer) {
                    answer = answer || firstCharMatch && isMatchMemorization(str, pattern, patternIndex, strIndex + 1, dp);
                }
            } else {
                answer = firstCharMatch && isMatchMemorization(str, pattern, patternIndex + 1, strIndex + 1, dp);
            }
        }
        dp[patternIndex][strIndex] = answer;
        return answer;
    }

    @Test
    public static void main(String[] args) {
        System.out.println(isMatch("adceb", "*a*b"));
        System.out.println(isMatch("acdcb", "a*c?b"));
        System.out.println(isMatch("", "******"));

        System.out.println(isMatch("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba", "a*******b"));
        Assertions.assertFalse(isMatch("babbbbaabababaabbababaababaabbaabababbaaababbababaaaaaabbabaaaabababbabbababbbaaaababbbabbbbbbbbbbaabbb",
                "b**bb**a**bba*b**a*bbb**aba***babbb*aa****aabb*bbb***a"));
        Assertions.assertTrue(isMatch("", "******"));
    }

    @Test
    void testwildcard() {
        Assertions.assertTrue(isMatch("", "******"));
        Assertions.assertTrue(isMatch("adceb", "*a*b"));
        Assertions.assertTrue(isMatch("aa", "*"));
        Assertions.assertFalse(isMatch("aa", "a"));
        Assertions.assertFalse(isMatch("cb", "?a"));
        Assertions.assertFalse(isMatch("acdcb", "a*c?b"));
        Assertions.assertFalse(isMatch("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba","a*******b"));
        Assertions.assertTrue(isMatch("abcabczzzde","*abc???de*"));

    }

    @Test
    void testwildcardDP() {
        Assertions.assertTrue(isMatchDP("", "******"));
        Assertions.assertTrue(isMatchDP("adceb", "*a*b"));
        Assertions.assertTrue(isMatchDP("aa", "*"));
        Assertions.assertFalse(isMatchDP("aa", "a"));
        Assertions.assertFalse(isMatchDP("cb", "?a"));
        Assertions.assertFalse(isMatchDP("acdcb", "a*c?b"));
        Assertions.assertFalse(isMatchDP("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba","a*******b"));
        Assertions.assertTrue(isMatchDP("abcabczzzde","*abc???de*"));
    }

    @Test
    void  testwildcardDPMemorizartion(){
        Assertions.assertTrue(isMatchMemorization("", "******"));
        Assertions.assertTrue(isMatchMemorization("adceb", "*a*b"));
        Assertions.assertTrue(isMatchMemorization("aa", "*"));
        Assertions.assertFalse(isMatchMemorization("aa", "a"));
        Assertions.assertFalse(isMatchMemorization("cb", "?a"));
        Assertions.assertFalse(isMatchMemorization("acdcb", "a*c?b"));
        Assertions.assertFalse(isMatchMemorization("aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba","a*******b"));
        Assertions.assertTrue(isMatchMemorization("abcabczzzde","*abc???de*"));

    }

    @Test
    void  testwildcardfailed(){
        Stopwatch stopwatch = Stopwatch.createStarted();
        Assertions.assertTrue(isMatch("aaaa","*a?"));
        Assertions.assertTrue(isMatchDP("aaaa","*a?"));
        System.out.println(" total time take: "+ stopwatch.elapsed(TimeUnit.MILLISECONDS)+ " Miliseconds" );

    }

    @Test
    void  testwildcardTimeLimit(){
        Stopwatch stopwatch = Stopwatch.createStarted();
        String str = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String pattern= "*aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa*";
        Assertions.assertFalse(isMatch(str,pattern));
        System.out.println(" total time take: "+ stopwatch.elapsed(TimeUnit.MILLISECONDS)+ " Miliseconds" );
    }

    @Test
    void  testwildcardTimeLimitDP(){
        Stopwatch stopwatch = Stopwatch.createStarted();
        String str = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String pattern= "*aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa*";
        Assertions.assertFalse(isMatchDP(str,pattern));
        System.out.println(" total time take: "+ stopwatch.elapsed(TimeUnit.MILLISECONDS)+ " Miliseconds" );

    }

    @Test
    void  testwildcardTimeLimitMemorization(){
        Stopwatch stopwatch = Stopwatch.createStarted();
        String str = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String pattern= "*aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa*";
        Assertions.assertFalse(isMatchMemorization(str,pattern));
        System.out.println(" total time take: "+ stopwatch.elapsed(TimeUnit.MILLISECONDS)+ " Miliseconds" );
    }

    @Test
    void  testUpdatePattern(){
        System.out.println(udpatePattern("a*******b"));

    }
}
