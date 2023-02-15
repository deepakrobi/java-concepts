package dynamicprogramming.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MinimumPartitionSet {
    public static int canPartition(int[] num) {
        int sum = 0;
        for (int i = 0; i < num.length; i++) {
            sum += num[i];
        }
        Integer[][] cache = new Integer[num.length][sum + 1];
        return canPartitionRecursive(num, 0, 0, 0, cache);
    }

    private static int canPartitionRecursive(int[] num, int currentIndex, int sum1, int sum2, Integer[][] cache) {
        // base check
        if (currentIndex == num.length) {
            return Math.abs(sum1 - sum2);
        }

        if (cache[currentIndex][sum1] != null) {
            return cache[currentIndex][sum1];
        }
        // recursive call after including the number at the currentIndex in the first set
        int diff1 = canPartitionRecursive(num, currentIndex + 1, sum1 + num[currentIndex], sum2, cache);

        // recursive call after including the number at the currentIndex in the second set
        int diff2 = canPartitionRecursive(num, currentIndex + 1, sum1, sum2 + num[currentIndex], cache);

        return cache[currentIndex][sum1] = Math.min(diff1, diff2);
    }

    @Test
    void test1() {
        Assertions.assertEquals(3,canPartition(new int[]{1, 2, 3, 9}));
    }

    @Test
    void test2() {
        Assertions.assertEquals(0,canPartition(new int[]{1, 2, 7, 1, 5}));
    }

    @Test
    void test3() {
        Assertions.assertEquals(92,canPartition(new int[]{1, 3, 100, 4}));
    }
}
