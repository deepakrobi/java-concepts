package dynamicprogramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Given a set of positive numbers, determine if a subset exists whose sum is equal to a given number ‘S’.
 *
 * Example 1: #
 * Input: {1, 2, 3, 7}, S=6
 * Output: True
 * The given set has a subset whose sum is '6': {1, 2, 3}
 * Example 2: #
 * Input: {1, 2, 7, 1, 5}, S=10
 * Output: True
 * The given set has a subset whose sum is '10': {1, 2, 7}
 * Example 3: #
 * Input: {1, 3, 4, 8}, S=6
 * Output: False
 * The given set does not have any subset whose sum is equal to '6'.
 */
public class SubSetSum {

    public static boolean canPartitionTwoPointer(int[] num, int sum){
        if (num == null || num.length < 1) {
            return false;
        }
        Arrays.sort(num);

        for(int i =0; i < num.length; i ++) {
            int left = i, right = num.length-1;
            int targetSum = sum - num[i];
            while (left < right) {
                int currentSum = num[left] + num[right];
                if (currentSum == targetSum) {
                    return true;
                } else if (currentSum < targetSum) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return false;
    }
    public static boolean canPartition(int[] num, int sum) {
        if (num == null || num.length < 1) {
            return false;
        }
        Boolean [] [] cache = new Boolean[num.length+1][sum+1];
        return canPartitionRecursive(cache,num, sum, 0);
    }

    public static boolean canPartitionRecursive(Boolean[][] cache,int[] num, int sum, int currentIndex) {

        if (currentIndex >= num.length || sum <0 ) {
            return false;
        }

        if (num[currentIndex] == sum || sum == 0) {
            return true;
        }

        if(cache[currentIndex][sum] != null){
            return cache[currentIndex][sum];
        }

        return (cache[currentIndex][sum] = canPartitionRecursive(cache,num, sum - num[currentIndex], currentIndex + 1))
                || (cache[currentIndex][sum] = canPartitionRecursive(cache,num, sum, currentIndex + 1));

    }

    @Test
    void test1() {
        Assertions.assertTrue(canPartition(new int[]{7,3,2,1}, 6));
        Assertions.assertTrue(canPartitionTwoPointer(new int[]{9,3,2,1,1}, 7));
    }

     @Test
    void test2() {
        Assertions.assertTrue(canPartition(new int[]{1, 2, 7, 1, 5}, 10));
         Assertions.assertTrue(canPartitionTwoPointer(new int[]{1, 2, 7, 1, 5}, 10));
    }

    @Test
    void test3() {
        Assertions.assertFalse(canPartition(new int[]{1, 3, 4, 8}, 6));
        Assertions.assertFalse(canPartitionTwoPointer(new int[]{1, 3, 4, 8}, 6));
    }
}
