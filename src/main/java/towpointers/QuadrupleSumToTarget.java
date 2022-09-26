package towpointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuadrupleSumToTarget {

    public static List<List<Integer>> searchQuadruplets(int[] arr, int target) {
        List<List<Integer>> quadruplets = new ArrayList<>();

        if(arr  == null || arr.length < 4){
            throw new IllegalArgumentException();
        }
        // sort the array
        Arrays.sort(arr);

        for (int i =0; i< arr.length -3; i ++) {
            // skip same element to avoid duplicate quadruplets
            if (i > 0 && arr[i] == arr[i - 1])
                continue;
            for (int j = i + 1; j < arr.length - 2; j++) {
                if (j > 0 && arr[j - 1] == arr[j]) {
                    continue;
                }
                searchPair(arr, arr[i],arr[j], target, j + 1, quadruplets);
            }
        }

        return quadruplets;
    }

    private static void searchPair(int[] arr, int first, int second, int target, int left, List<List<Integer>> quadruplets) {
        int right = arr.length -1;

        while (left < right) {
            int currentSum = first + second + arr[left] + arr[right];
            if (currentSum == target) {
                // found quadruplets.
                quadruplets.add(Arrays.asList(first,second, arr[left], arr[right]));
                left++;
                right--;
                while (left < right && arr[left - 1] == arr[left]) {
                    left++;
                }

                while (left < right && arr[right] == arr[right + 1]) {
                    right--;
                }
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(QuadrupleSumToTarget.searchQuadruplets(new int[] { 4, 1, 2, -1, 1, -3 }, 1));
        System.out.println(QuadrupleSumToTarget.searchQuadruplets(new int[] { 2, 0, -1, 1, -2, 2 }, 2));
    }
}
