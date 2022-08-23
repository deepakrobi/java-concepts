package practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * Example 1:
 *
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 *
 * Example 2:
 *
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 *
 * Example 3:
 *
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 *
 */
public class PatternMatching {

    public static String removeNonAlphanumeric(String str)
    {
        // replace the given string
        // with empty string
        // except the pattern "[^a-zA-Z0-9]"
        str = str.replaceAll(
                "[^a-zA-Z0-9]", "");

        str.replaceAll("[^a-zA-Z0-9]","");
        // return string
        return str;
    }

    public void regExMatch(String str){
        String regExPattern = "[a]";
        Pattern pattern = Pattern.compile(regExPattern);

        Matcher matcher = pattern.matcher(str);

        System.out.println(str.matches(regExPattern));
        System.out.println(matcher.find());


    }
    public boolean isMatch(String s, String p) {
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }

    public boolean isMatchManual(String s, String p) {
       if(!p.contains("*") && !p.contains(".")){
            return  s.matches(p);
        }

       if(p.equals("*") || p.contains(".*")) {
           return true;
       }

      String [] splits =  p.split("\\*");
       for(int i =0;i<splits.length;i++){
           System.out.println(splits[i]);
       }

       if(p.contains("*")){
           String subStr = s.substring(0, p.indexOf("*"));
           if(subStr.contains(".")){
                return true;
           }else{
               return s.contains(subStr);
           }
       }
       return false;
    }

    public  static void main (String [] args){
        PatternMatching patternMatching = new PatternMatching();
       // patternMatching.regExMatch("abc");
       /* System.out.println(patternMatching.isMatch("aa","a*"));
        System.out.println(patternMatching.isMatch("ab",".*"));
        System.out.println(patternMatching.isMatch("aa","a"));
        System.out.println("Manual: "+ patternMatching.isMatchManual("aa","a"));
        System.out.println("Manual: "+ patternMatching.isMatchManual("aa","a*"));

        System.out.println("Manual: "+ patternMatching.isMatchManual("ab",".*"));*/
        System.out.println("Manual: "+ patternMatching.isMatchManual("mississippi", "mis*is*p*."));




    }
}
