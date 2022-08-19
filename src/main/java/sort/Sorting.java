package sort;

import java.util.Arrays;
import java.util.Comparator;

public class Sorting {
    public void sortByLength(String[] arr) {
        // Sorts a list of string by length of each string
        Arrays.sort(arr, new StringCompare());
    }
}

class StringCompare implements Comparator<String> {
    public int compare(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return 1;
        } else if (s1.length() < s2.length()) {
            return -1;
        }
        return 0;
    }
}
