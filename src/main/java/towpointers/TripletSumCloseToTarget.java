package towpointers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Problem Statement
 *
 * Given an array of unsorted numbers and a target number, find a triplet in the array whose sum is as close to the
 * target number as possible, return the sum of the triplet. If there are more than one such triplet, return the sum of
 * the triplet with the smallest sum.
 *
 * Example 1:
 *
 * Input: [-2, 0, 1, 2], target=2
 * Output: 1
 * Explanation: The triplet [-2, 1, 2] has the closest sum to the target.
 * Example 2:
 *
 * Input: [-3, -1, 1, 2], target=1
 * Output: 0
 * Explanation: The triplet [-3, 1, 2] has the closest sum to the target.
 * Example 3:
 *
 * Input: [1, 0, 1, 1], target=100
 * Output: 3
 * Explanation: The triplet [1, 1, 1] has the closest sum to the target.
 * Example 4:
 *
 * Input: [0, 0, 1, 1, 2, 6], target=5
 * Output: 4
 * Explanation: There are two triplets with distance '1' from target: [1, 1, 2] & [0,0, 6]. Between these two triplets,
 * the correct answer will be [1, 1, 2] as it has a sum '4' which is less than the sum of the other triplet which is '6'
 * This is because of the following requirement: 'If there are more than one such triplet, return the sum of the triplet
 * with the smallest sum.
 */
public class TripletSumCloseToTarget {

    public static int searchTriplet(int[] arr, int targetSum) {
        int output = 0;
        if (arr == null || arr.length < 3) {
            return output;
        }
        Arrays.sort(arr);
        int smallestDiff = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 2; i++) {
            smallestDiff = searchClosesetDiff(arr, arr[i], targetSum, i + 1, smallestDiff);
            if (smallestDiff == 0) {
                // there is no need to search anymore as we have found the exact match with target Sum
                break;
            }
        }
        return targetSum - smallestDiff;
    }

    private static int searchClosesetDiff(int[] arr,int currentNumber, int targetSum, int left, int smallestDiff) {
        int right = arr.length - 1;
        while (left < right) {
            int targetDiff = targetSum - (currentNumber + arr[left] + arr[right]);
            if (targetDiff == 0) {
                // we have found the exact match, so return the diff
                return targetDiff;
            }

            if (Math.abs(targetDiff) < Math.abs(smallestDiff)
                    || (Math.abs(targetDiff) == Math.abs(smallestDiff) && targetDiff > smallestDiff)) {
                smallestDiff = targetDiff;
            }

            if (targetDiff > 0) {
                left++;
            } else {
                right--;
            }
        }

        return smallestDiff;
    }

    @Test
    void test1(){
        Assertions.assertEquals(1,TripletSumCloseToTarget.searchTriplet(new int[]{-2, 0, 1, 2}, 2));
    }

    @Test
    void test2(){
        Assertions.assertEquals(0,TripletSumCloseToTarget.searchTriplet(new int[]{-3, -1, 1, 2}, 1));
    }
    @Test
    void test3(){
        Assertions.assertEquals(3,TripletSumCloseToTarget.searchTriplet(new int[]{1, 0, 1, 1}, 100));
    }
    @Test
    void test4(){
        Assertions.assertEquals(4,TripletSumCloseToTarget.searchTriplet(new int[]{0, 0, 1, 1, 2, 6}, 5));
    }

    @Test
    void test5(){
        Assertions.assertEquals(0,TripletSumCloseToTarget.searchTriplet(new int[]{0,0,0}, 1));
    }

    @Test
    void test6(){
        Assertions.assertEquals(2, TripletSumCloseToTarget.searchTriplet(new int[]{-1, 2, 1, -4}, 1));
    }

    @Test
    void test7(){
        Assertions.assertEquals(3, TripletSumCloseToTarget.searchTriplet(new int[]{0,1,2}, 3));
    }
}
