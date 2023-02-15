package dynamicprogramming;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 * Example 3:
 *
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 * Constraints:
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 */
public class CoinChange {

   private  Integer[] cache;
    public int coinChange(int[] coins, int amount) {
        cache = new Integer[amount+1];
        return coinChangeHelper( coins, amount);
    }

    private int coinChangeHelper( int[] coins, int remain) {
        if (remain < 0) return -1;
        if (remain == 0) return 0;

        if (cache[remain] != null) {
            return cache[remain];
        }
        int minCount = Integer.MAX_VALUE;
        for (int coin : coins) {
            int count = coinChangeHelper(coins, remain - coin);
            if (count != -1) {
                minCount = Math.min(minCount, count + 1);
            }
        }

        return cache[remain] = minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

    @Test
    void test1() {
        int [] coins = {1,5};
        Assertions.assertEquals(3,new CoinChange().coinChange(coins,11));
    }

    @Test
    void test2() {
        int [] coins = {2};
        Assertions.assertEquals(-1,new CoinChange().coinChange(coins,3));
    }

    @Test
    void test3() {
        int [] coins = {1};
        Assertions.assertEquals(0,new CoinChange().coinChange(coins,0));
    }
}

// Time Limit Exceeded
