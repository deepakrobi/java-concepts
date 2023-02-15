package dynamicprogramming.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an infinite supply of ‘n’ coin denominations and a total money amount, we are asked to find the total number of
 * distinct ways to make up that amount.
 *
 * Example:
 * Denominations: {1,2,3}
 * Total amount: 5
 * Output: 5
 * Explanation: There are five ways to make the change for '5', here are those ways:
 *   1. {1,1,1,1,1}
 *   2. {1,1,1,2}
 *   3. {1,2,2}
 *   4. {1,1,3}
 *   5. {2,3}
 */
public class CoinChange {

    public static int countofWays(int[] input, int total) {
        return countRecursive(input, total, 0);
    }

    private static int countRecursive(int[] input, int total, int currentIndex) {
        if (input == null || input.length < 1 || currentIndex >= input.length) {
            return 0;
        }

        if (total == 0) {
            return 1;
        }

        int count1 = 0;
        if (input[currentIndex] <= total) {
            count1 = countRecursive(input, total - input[currentIndex], currentIndex);
        }

        int count2 = countRecursive(input, total, currentIndex + 1);

        return count1 + count2;
    }


    @Test
    void test1(){
            Assertions.assertEquals(5, countofWays(new int[]{1, 2, 3}, 5));
    }

}
