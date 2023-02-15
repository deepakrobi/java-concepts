package dynamicprogramming.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an infinite supply of ‘n’ coin denominations and a total money amount, we are asked to find the minimum number
 * of coins needed to make up that amount.
 *
 * Example 1:
 *
 * Denominations: {1,2,3}
 * Total amount: 5
 * Output: 2
 * Explanation: We need a minimum of two coins {2,3} to make a total of '5'
 * Example 2:
 *
 * Denominations: {1,2,3}
 * Total amount: 11
 * Output: 4
 * Explanation: We need a minimum of four coins {2,3,3,3} to make a total of '11'
 */
public class MinimumCoinChange {

    public static int countofWays(int[] input, int total) {
        if (input == null || input.length < 1) {
            return 0;
        }
        return countRecursive(input, total, 0);
    }

    private static int countRecursive(int[] input, int total, int currentIndex) {

        if (total == 0) {
            return 0;
        }

        if (input == null || input.length < 1 || currentIndex >= input.length) {
            return Integer.MAX_VALUE;
        }

        int count1 = Integer.MAX_VALUE;;

        if (input[currentIndex] <= total) {
            int res = countRecursive(input, total - input[currentIndex], currentIndex);
            if (res != Integer.MAX_VALUE) {
                count1 = res+1;
            }
        }

        int count2 = countRecursive(input, total, currentIndex + 1);

        return Math.min(count1, count2);
    }

    private static int countChangeRecursive(int[] denominations, int total, int currentIndex) {
        // base check
        if (total == 0)
            return 0;

        if(denominations.length == 0 || currentIndex >= denominations.length)
            return Integer.MAX_VALUE;

        // recursive call after selecting the coin at the currentIndex
        // if the coin at currentIndex exceeds the total, we shouldn't process this
        int count1 = Integer.MAX_VALUE;
        if( denominations[currentIndex] <= total ) {
            int res = countChangeRecursive(denominations,
                    total - denominations[currentIndex], currentIndex);
            if(res != Integer.MAX_VALUE){
                count1 = res+1;
            }
        }

        // recursive call after excluding the coin at the currentIndex
        int count2 = countChangeRecursive(denominations, total, currentIndex + 1);

        return Math.min(count1, count2);
    }

    @Test
    void test1(){
        Assertions.assertEquals(2, countofWays(new int[]{1, 2, 3}, 5));
    }

    @Test
    void test2(){
        Assertions.assertEquals(2, countofWays(new int[]{4,1,2}, 5));
    }
}
