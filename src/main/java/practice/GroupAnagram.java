package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * Example 2:
 *
 * Input: strs = [""]
 * Output: [[""]]
 * Example 3:
 *
 * Input: strs = ["a"]
 * Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 */
public class GroupAnagram {

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> anagramGroup = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            String sortString = sortString(str);
            if (!map.containsKey(sortString)) {
                map.put(sortString, new ArrayList<>());
            }
            map.get(sortString).add(str);
        }
        anagramGroup.addAll(map.values());
        return anagramGroup;
    }

    static String sortString(String input){
        char [] chars = input.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
       System.out.println( groupAnagrams(input));
    }

}
