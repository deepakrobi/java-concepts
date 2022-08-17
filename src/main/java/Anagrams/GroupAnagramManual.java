package Anagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagramManual {

    public  List <List<String>> getResult (String[] strings){
         List <List<String>> result = new ArrayList<>();
        HashMap <String, List<String>> map = new HashMap<>();

            for(String str: strings){
                String sortedString = sortedString(str);
                if(!map.containsKey(sortedString)){
                    map.put(sortedString,new ArrayList<>());
                }
                map.get(sortedString).add(str);
            }
            result.addAll(map.values());

        return  result;
    }

    private  String sortedString(String input){
        char[] characters = input.toCharArray();
        Arrays.sort(characters);
        return new String(characters);
    }

    public static void main (String[] args){
        String[] input = {"abc","dbaa", "abdd", "cab"};

        System.out.println(new GroupAnagramManual().getResult(input));
    }
}
