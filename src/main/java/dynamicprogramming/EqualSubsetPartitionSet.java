package dynamicprogramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Given a set of positive numbers, find if we can partition it into two subsets such that the sum of elements in both
 * subsets is equal.
 *
 * Example 1:
 *
 * Input: {1, 2, 3, 4}
 * Output: True
 * Explanation: The given set can be partitioned into two subsets with equal sum: {1, 4} & {2, 3}
 * Example 2:
 *
 * Input: {1, 1, 3, 4, 7}
 * Output: True
 * Explanation: The given set can be partitioned into two subsets with equal sum: {1, 3, 4} & {1, 7}
 * Example 3:
 *
 * Input: {2, 3, 4, 6}
 * Output: False
 * Explanation: The given set cannot be partitioned into two subsets with equal sum.
 */
public class EqualSubsetPartitionSet {

    public static boolean canPartitionTwoPointer( int[] num ) {
        if (num == null || num.length < 2) {
            return false;
        }
        Arrays.sort(num);


        int sum = 0;
        for (int i = 0; i < num.length; i++) {
            sum += num[i];
        }
        if (sum % 2 != 0) { // if sum is not even than it can not be divided into 2 subset.
            return false;
        }

        for (int i = 0; i < num.length; i++) {
            int left = i, right = num.length - 1;
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

    public static boolean canPartition(int[] num) {
        if (num == null || num.length < 2) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < num.length; i++) {
            sum += num[i];
        }
        if (sum % 2 != 0) { // if sum is not even than it can not be divided into 2 subset.
            return false;
        }
        Boolean[][] cache = new Boolean[num.length + 1][sum / 2 + 1];
        return canPartitionRecursiveNew(cache, num, sum / 2, 0);
    }

    private static boolean canPartitionRecursive(Boolean[][] cache, int[] num, int sum, int currentIndex) {
        if (sum == 0) {
            return true;
        }
        if (currentIndex >= num.length) {
            return false;
        }

        if (cache[currentIndex][sum] != null) {
            return cache[currentIndex][sum];
        }

        if (num[currentIndex] <= sum) {
            if (canPartitionRecursive(cache, num, sum - num[currentIndex], currentIndex + 1)) {
                return cache[currentIndex][sum] = true;
            }
        }

        return cache[currentIndex][sum] = canPartitionRecursive(cache, num, sum, currentIndex + 1);
    }

    private static boolean canPartitionRecursiveNew(Boolean[][] cache, int[] num, int sum, int currentIndex) {
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
    void test1(){
        Assertions.assertTrue(canPartition(new int[]{1, 2, 3, 4}));
        Assertions.assertTrue(canPartitionTwoPointer(new int[]{1, 2, 3, 4}));
    }

    @Test
    void test2(){
        Assertions.assertTrue(canPartition(new int[]{1, 1, 3, 4, 7}));
        Assertions.assertTrue(canPartitionTwoPointer(new int[]{1, 1, 3, 4, 7}));
    }

    @Test
    void test3(){
        Assertions.assertFalse(canPartition(new int[]{2, 3, 4, 6}));
        Assertions.assertFalse(canPartitionTwoPointer(new int[]{2, 3, 4, 6}));
    }
}
