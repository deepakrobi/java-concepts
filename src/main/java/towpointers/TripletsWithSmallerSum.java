package towpointers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Problem Statement
 *
 * Given an array arr of unsorted numbers and a target sum, count all triplets in it such that arr[i] + arr[j] + arr[k] < target
 * where i, j, and k are three different indices. Write a function to return the count of such triplets.
 *
 * Example 1:
 * Input: [-1, 0, 2, 3], target=3
 * Output: 2
 * Explanation: There are two triplets whose sum is less than the target: [-1, 0, 3], [-1, 0, 2]
 *
 * Example 2:
 * Input: [-1, 4, 2, 1, 3], target=5
 * Output: 4
 * Explanation: There are four triplets whose sum is less than the target:
 *   [-1, 1, 4], [-1, 1, 3], [-1, 1, 2], [-1, 2, 3]
 */
public class TripletsWithSmallerSum {
    public static int countTriplets(int[] arr, int target) {
        int count = 0;
        if (arr == null || arr.length < 3) {
            throw new IllegalArgumentException("Input is not valid");
        }

        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {
            count+=count(arr, target - arr[i], i + 1);
        }


        return count;
    }

    private static int count(int[] arr,  int target, int left){
        int count =0;
        int right = arr.length -1;

        while (left < right) {
            int currentSum = arr[left] + arr[right];
            if (currentSum < target) {
                // we found one triplet, increase the count
                count = right - left;
                left++;
            } else {
                right--;
            }
        }
        return count;
    }

    @Test
    void test1(){
        Assertions.assertEquals(2, countTriplets(new int[]{-1, 0, 2, 3}, 3));
    }
}
