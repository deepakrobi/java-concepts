package dynamicprogramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Problem Statement
 *
 * Given a set of positive numbers, find the total number of subsets whose sum is equal to a given number ‘S’.
 *
 * Example 1: #
 * Input: {1, 1, 2, 3}, S=4
 * Output: 3
 * The given set has '3' subsets whose sum is '4': {1, 1, 2}, {1, 3}, {1, 3}
 * Note that we have two similar sets {1, 3}, because we have two '1' in our input.
 * Example 2: #
 * Input: {1, 2, 7, 1, 5}, S=9
 * Output: 3
 * The given set has '3' subsets whose sum is '9': {2, 7}, {1, 7, 1}, {1, 2, 1, 5}
 */
public class CountOfSubset {


    public static int countSubsets(int[] num, int sum) {
        int count = 0;
        if(num == null || num.length <1){
            return count;
        }

        return countSubsetsRecursive(num,sum,0);
    }

    private static int countSubsetsRecursive(int[] num, int sum, int currentIndex) {

        if (currentIndex == num.length || sum < num[currentIndex]) {
            return 0;
        }

        if (num[currentIndex] == sum || sum == 0) {
            return 1;
        }

        return countSubsetsRecursive(num, sum - num[currentIndex], currentIndex + 1)
                + countSubsetsRecursive(num, sum, currentIndex + 1);

    }

    private static int countSubsetsRecursiveNew(int[] num, int sum, int currentIndex) {
        // base checks
        if (sum == 0)
            return 1;

        if(num.length == 0 || currentIndex >= num.length)
            return 0;

        // recursive call after selecting the number at the currentIndex
        // if the number at currentIndex exceeds the sum, we shouldn't process this
        int sum1 = 0;
        if( num[currentIndex] <= sum )
            sum1 = countSubsetsRecursive(num, sum - num[currentIndex], currentIndex + 1);

        // recursive call after excluding the number at the currentIndex
        int sum2 = countSubsetsRecursive(num, sum, currentIndex + 1);

        return sum1 + sum2;
    }

    public static int countSubsetsTwoPointer(int[] num, int sum) {
        int count = 0;
        if (num == null || num.length < 1) {
            return count;
        }
        Arrays.sort(num);

        for (int i = 0; i < num.length; i++) {
            int left = i, right = num.length - 1;
            int targetSum = sum - num[i];
            while (left < right) {
                int currentSum = num[left] + num[right];
                if (currentSum == targetSum) {
                    count++;
                    break;
                } else if (currentSum < targetSum) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return count;
    }

    @Test
    void test1() {
        Assertions.assertEquals(3, countSubsets(new int[]{1, 1, 2, 3},4 ));
    }

    @Test
    void test2() {
        Assertions.assertEquals(3, countSubsetsTwoPointer(new int[]{1, 2, 7, 1, 5},9 ));
        Assertions.assertEquals(3, countSubsets(new int[]{1, 2, 7, 1, 5},9 ));
    }
}
