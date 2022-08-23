package practice;

import com.google.common.base.Stopwatch;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

public class LongestPalindromicSubstring {
    public boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }
        if(str.length() ==1){
            return  true;
        }

        int i = 0;
        int j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public String longestPalindrome(String str) {
        String result = new String();
        String temp = new String();
        int i =0, j = 0;
        if(str.length() <2){
            return  str;
        }

        Set<Character> strSet = str.chars().mapToObj(c->(char)c).collect(Collectors.toSet());
        if(strSet.size() <1 ){
            return str;
        }
        if(isPalindrome(str)){
            return str;
        }
            while(i< str.length()){
                 temp = String.valueOf(str.charAt(i));
               for(j=i+1; j <str.length(); j ++){
                    temp+= str.charAt(j);
                    if(temp.length() > result.length() && isPalindrome(temp)){
                        result = temp.length()> result.length()? temp: result;
                    }
                }
               i++;
            }
        result = temp.length()> result.length()? temp: result;
        return result;
    }

    public static void main (String [] args){
        LongestPalindromicSubstring palindromicSubstring = new LongestPalindromicSubstring();
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println(palindromicSubstring.longestPalindrome("xiqhechagdpbcdthaafmcnplenylepawbafsmxqlwhzgqmuemwolgoockcafchdsfggulwfzwwkvivnwgbelbbydzfkcfsschvbantskuosunhqihmqjmzgavfnonwhwrkfxgcbowfsebthbrhhklxxyoxiphrgxqodulrbbvdwcclpyjhljgyypztbqzkiyzbfnvnoargyyakaidkiyleurvjbadzwqjtrluayhblhdokmwrwhassruxpftwlbalfvwxtfcqibywsusrlwmbcibvgwnmmdmuhswuperbjoxarhqcpcebbtyhnrouvuwftspmzsmdhfcqovffkuikzrcweffmpnjldoalhcvqvjavllvajvqvchlaodljnpmffewcrzkiukffvoqcfhdmszmpstfwuvuornhytbbecpcqhraxojbrepuwshumdmmnwgvbicbmwlrsuswybiqcftxwvflablwtfpxurssahwrwmkodhlbhyaulrtjqwzdabjvruelyikdiakayygraonvnfbzyikzqbtzpyygjlhjyplccwdvbbrludoqxgrhpixoyxxlkhhrbhtbesfwobcgxfkrwhwnonfvagzmjqmhiqhnusoukstnabvhcssfckfzdybblebgwnvivkwwzfwluggfsdhcfackcooglowmeumqgzhwlqxmsfabwapelynelpncmfaahtdcbpdgahcehqix"));
        System.out.println( "Total time taken : "+ stopwatch.elapsed(TimeUnit.MILLISECONDS));
        //System.out.println(palindromicSubstring.longestPalindrome("babad"));
    }
}
