package Anagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {
    public List<List<String>> result = new ArrayList<>();

    public List<List<String>> findGroupAnagram(String[] strings) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            char[] characters = str.toCharArray();
            String sorted = sortString(str);
            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<>());
            }
            map.get(sorted).add(str);
        }
        result.addAll(map.values());
        return result;
    }

    private String sortString(String input) {
        if (input == null) {
            return null;
        }
        char[] characters = input.toCharArray();
        Arrays.sort(characters);
        return new String(characters);
    }

    public static void main(String[] args) {
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        String[] words = {"pop", "bat", "tab", "opp"};
        System.out.println(groupAnagrams.findGroupAnagram(words));
    }
}
