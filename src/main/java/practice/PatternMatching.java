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
        // Two dimensional [pattern = row][string = column]
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];

        // i == pattern = row  j = string = column
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 && j == 0) {
                    // for first cell. it is always true;
                    dp[i][j] = true;
                } else if (i == 0) {
                    // first row always false except first cell which is covered in if before this.
                    dp[i][j] = false;
                } else if (j == 0) {
                    /** 1st column rule.
                     * Any pattern char and . = false;
                     * if pattern char is * then look 2 rows above
                     */
                    char pc = p.charAt(i - 1);
                    if (pc == '*') {
                        dp[i][j] = dp[i - 2][j];
                    } else {
                        dp[i][j] = false;
                    }
                } else {
                    /** all grid rule
                     * if pattern char = string char then look diagonally up.
                     * if pattern char != string char then false.
                     * if pattern char = . then look diagonally up. ( i.e. go back 1 row and 1 column)
                     * if pattern char = *
                     *     then
                     *         look 2 rows above.
                     *          if pattern last char = . or pattern last char = string char
                     *              then look 1 row horizontally (i.e. go back 1 column)
                     */
                    char pc = p.charAt(i - 1);
                    char sc = s.charAt(j - 1);
                    if (pc == '*') {
                        dp[i][j] = dp[i - 2][j];
                        char pslc = p.charAt(i - 2);
                        if (pslc == '.' || pslc == sc) {
                            dp[i][j] = dp[i][j] || dp[i][j - 1];
                        }
                    } else if (pc == '.' || pc == sc) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
        }

        return dp[p.length()][s.length()];
    }

    public boolean isMatchManual(String str, String pattern) {
        if (pattern.isEmpty()) return str.isEmpty();
        char STAR = '*';
        char DOT = '.';
        // Two dimensional [pattern = row][string = column]
        boolean[][] dp = new boolean[pattern.length() + 1][str.length() + 1];

        for (int row = 0; row < dp.length; row++) {
            for (int column = 0; column < dp[0].length; column++) {
                if (row == 0 && column == 0) {
                    // first cell is true.
                    dp[row][column] = true;
                } else if (row == 0) {
                    // first row is false except first cell.
                    dp[row][column] = false;
                } else if (column == 0) {
                    // first column is false except * .If * then look 2 rows above.
                    char pc = pattern.charAt(row - 1);
                    if (pc == STAR) {
                        dp[row][column] = dp[row - 2][column];
                    } else {
                        dp[row][column] = false;
                    }
                } else {
                    /** all grid rule
                     * if pattern char = string char then look diagonally up.
                     * if pattern char != string char then false.
                     * if pattern char = . then look diagonally up. ( i.e. go back 1 row and 1 column)
                     * if pattern char = *
                     *     then
                     *         look 2 rows above.
                     *          if pattern patttern char = . or pattern pattern char = string char
                     *              then look 1 row horizontally (i.e. go back 1 column)
                     */
                    char pc = pattern.charAt(row - 1);
                    char sc = str.charAt(column - 1);
                    if (pc == STAR) {
                        dp[row][column] = dp[row - 2][column];
                        char previousPatternChar = pattern.charAt(row - 2);
                        if (previousPatternChar == DOT || previousPatternChar == sc) {
                            dp[row][column] = dp[row][column] || dp[row][column - 1];
                        }
                    } else if (pc == DOT || pc == sc) {
                        dp[row][column] = dp[row - 1][column - 1];
                    } else {
                        dp[row][column] = false;
                    }
                }
            }
        }
        return dp[pattern.length()][str.length()];
    }

    public boolean isMatchRecursive(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >1 && pattern.charAt(1) == '*') {
            return (isMatchRecursive(text, pattern.substring(2)) ||
                    (first_match && isMatchRecursive(text.substring(1), pattern)));
        } else {
            return first_match && isMatchRecursive(text.substring(1), pattern.substring(1));
        }
    }

    public boolean isMatchRecursiveManual(String text, String pattern) {
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }
        boolean firstCharMatch = (text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.');

        if (pattern.length() > 1 && pattern.charAt(1) == '*') {
            return (isMatchRecursiveManual(text, pattern.substring(2))) ||
                    (firstCharMatch && isMatchRecursiveManual(text.substring(1), pattern));
        } else {
            return firstCharMatch && isMatchRecursiveManual(text.substring(1), pattern.substring(1));
        }
    }

    public  static void main (String [] args){
        PatternMatching patternMatching = new PatternMatching();
        System.out.println(patternMatching.isMatch("mississippi", "mis*i.*p*i"));
        System.out.println(patternMatching.isMatch("mississippi", "mis*is*p*i"));

        System.out.println(patternMatching.isMatchManual("mississippi", "mis*i.*p*i"));
        System.out.println(patternMatching.isMatchManual("mississippi", "mis*is*p*i"));

        System.out.println(patternMatching.isMatchRecursive("mississippi", "mis*i.*p*i"));
        System.out.println(patternMatching.isMatchRecursive("mississippi", "mis*is*p*i"));
    }
}
