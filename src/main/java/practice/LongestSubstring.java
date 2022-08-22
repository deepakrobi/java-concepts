package practice;

import java.util.HashMap;

public class LongestSubstring {
    public int longestLengthAcquireRelease(String str){
        int result = 0;
        int acquire=-1;
        int release = -1;

        HashMap<Character, Integer> map = new HashMap<>();
        while(true){
            boolean acquireLoop= false;
            boolean releaseLoop = false;

            //acquire until invalid
            while(acquire < str.length()-1){
                acquire ++;
                acquireLoop= true;
                char ch = str.charAt(acquire);
                map.put(ch,map.getOrDefault(ch,0)+1);
                if(map.get(ch) ==2) {
                    break;
                } else {
                    int length = acquire - release;
                    result = length > result ? length : result;
                }
            }

            //release until valid
            while (release < acquire){
                releaseLoop = true;
                release ++;
                char ch = str.charAt(release);
                map.put(ch,map.get(ch)-1);
                if(map.get(ch) == 1){
                    break;
                }
            }

            if(!acquireLoop && !releaseLoop){
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LongestSubstring longestSubstring = new LongestSubstring();
        String str = "abccacbb";
        System.out.println(" Longest subString length for "+ str + " : "+ longestSubstring.longestLengthAcquireRelease(str));

        str = "bbbbb";
        System.out.println(" Longest subString length for "+ str + " : "+ longestSubstring.longestLengthAcquireRelease(str));

        str = "pwwkew";
        System.out.println(" Longest subString length for "+ str + " : "+ longestSubstring.longestLengthAcquireRelease(str));

        str = "abb";
        System.out.println(" Longest subString length for "+ str + " : "+ longestSubstring.longestLengthAcquireRelease(str));

        str = " ";
        System.out.println(" Longest subString length for "+ str + " : "+ longestSubstring.longestLengthAcquireRelease(str));

        str = "au";
        System.out.println(" Longest subString length for "+ str + " : "+ longestSubstring.longestLengthAcquireRelease(str));

        str = "dvdf";
        System.out.println(" Longest subString length for "+ str + " : "+ longestSubstring.longestLengthAcquireRelease(str));

        str = "";
        System.out.println(" Longest subString length for "+ str + " : "+ longestSubstring.longestLengthAcquireRelease(str));

        str = "xxzqi";
        System.out.println(" Longest subString length for "+ str + " : "+ longestSubstring.longestLengthAcquireRelease(str));

        str = "ckilbkd";
        System.out.println(" Longest subString length for "+ str + " : "+ longestSubstring.longestLengthAcquireRelease(str));

    }
}


