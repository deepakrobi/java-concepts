package dynamicprogramming.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * There are n houses built in a line. A thief wants to steal the maximum possible money from these houses. The only
 * restriction the thief has is that he can’t steal from two consecutive houses, as that would alert the security system
 * How should the thief maximize his stealing?
 *
 * Problem Statement
 *
 * Given a number array representing the wealth of n houses, determine the maximum amount of money the thief can steal
 * without alerting the security system.
 *
 * Example 1:
 * Input: {2, 5, 1, 3, 6, 2, 4}
 * Output: 15
 * Explanation: The thief should steal from houses 5 + 6 + 4
 *
 * Example 2:
 * Input: {2, 10, 14, 8, 1}
 * Output: 18
 * Explanation: The thief should steal from houses 10 + 8
 */
public class HouseThief {

    public static  int rob(int[] house) {
        Integer[] cache = new Integer[house.length];
        return robRecursive(house, 0, cache);
    }

    private static int robRecursive(int[] house, int currentHouse,   Integer []  cache) {
        if (currentHouse >= house.length) {
            return 0;
        }
        if (cache[currentHouse] != null) {
            return cache[currentHouse];
        }
        int profitFromRobbingCurrentHouse = house[currentHouse] + robRecursive(house, currentHouse + 2, cache);
        int profitFromNotRobbingCurrentHouse = robRecursive(house, currentHouse + 1, cache);

        return Math.max(profitFromRobbingCurrentHouse, profitFromNotRobbingCurrentHouse);
    }

    @Test
    void test1() {
        Assertions.assertEquals(15, rob(new int[]{2, 5, 1, 3, 6, 2, 4}));
    }

    @Test
    void test2() {
        Assertions.assertEquals(18, rob(new int[]{2, 10, 14, 8, 1}));
    }
}
