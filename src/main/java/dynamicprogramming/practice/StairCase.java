package dynamicprogramming.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given a stair with ‘n’ steps, implement a method to count how many possible ways are there to reach the top of the
 * staircase, given that, at every step you can either take 1 step, 2 steps, or 3 steps.
 *
 * Example 1:
 * Number of stairs (n) : 3
 * Number of ways = 4
 * Explanation: Following are the four ways we can climb : {1,1,1}, {1,2}, {2,1}, {3}
 *
 * Example 2:
 * Number of stairs (n) : 4
 * Number of ways = 7
 * Explanation: Following are the seven ways we can climb : {1,1,1,1}, {1,1,2}, {1,2,1}, {2,1,1},
 * {2,2}, {1,3}, {3,1}
 */
public class StairCase {

    public static int count(int n) {
        Integer[] cache = new Integer[n + 1];
        return countRecursive(n, cache);
    }

    private static int countRecursive(int n, Integer[] cache) {
        if (n == 0) {
            return 1;
        }
        if (n < 3) {
            return n;
        }

        if (cache[n] != null) {
            return cache[n];
        }

        return cache[n] = countRecursive(n - 1, cache) + countRecursive(n - 2, cache) + countRecursive(n - 3, cache);
    }

    @Test
    void test1() {
        Assertions.assertEquals(4, count(3));
    }

    @Test
    void test2() {
        Assertions.assertEquals(7, count(4));
    }
}
