package dynamicprogramming.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * We are given a ribbon of length ‘n’ and a set of possible ribbon lengths. We need to cut the ribbon into the maximum
 * number of pieces that comply with the above-mentioned possible lengths. Write a method that will return the count of
 * pieces.
 *
 * Example 1:
 * n: 5
 * Ribbon Lengths: {2,3,5}
 * Output: 2
 * Explanation: Ribbon pieces will be {2,3}.
 *
 * Example 2:
 * n: 7
 * Ribbon Lengths: {2,3}
 * Output: 3
 * Explanation: Ribbon pieces will be {2,2,3}.
 *
 * Example 3:
 * n: 13
 * Ribbon Lengths: {3,5,7}
 * Output: 3
 * Explanation: Ribbon pieces will be {3,3,7}.
 */
public class MaximumRibbon {

    public static int countRibbonPieces(int[] ribbonLengths, int total) {
        return countRecursive(ribbonLengths, total, 0);
    }

    private static int countRecursive(int[] ribbons, int total, int currentIndex) {
        if (total == 0) {
            return 0;
        }

        if (ribbons == null || currentIndex >= ribbons.length) {
            return Integer.MIN_VALUE;
        }
        int count1 = Integer.MIN_VALUE;

        if (ribbons[currentIndex] <= total) {
            int res = countRecursive(ribbons, total - ribbons[currentIndex], currentIndex);
            if (res != Integer.MIN_VALUE) {
                count1 = res + 1;
            }
        }

        int count2 = countRecursive(ribbons, total, currentIndex + 1);

        return Math.max(count1, count2);
    }

    @Test
    void test1() {
        Assertions.assertEquals(2, countRibbonPieces(new int[] {2,3,5},5));
    }

    @Test
    void test2() {
        Assertions.assertEquals(3, countRibbonPieces(new int[] {2,3},7));
    }

    @Test
    void test3() {
        Assertions.assertEquals(3, countRibbonPieces(new int[] {3,5,7},13));
    }
}
