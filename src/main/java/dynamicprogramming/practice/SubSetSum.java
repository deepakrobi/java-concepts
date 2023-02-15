package dynamicprogramming.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import recursion.MyCounter;

public class SubSetSum {

    static MyCounter counter = new MyCounter( 0);
    public static boolean subsetExists(int[] input, int sum) {
        Boolean [] [] cache = new Boolean[input.length][sum +1];
        return subsetExistsRecursive(input,sum,0,cache);
    }

    private static boolean subsetExistsRecursive(int [] input, int sum, int currentIndex, Boolean [] [] cache) {
        if (input == null || sum < 0 || currentIndex >= input.length) {
            return false;
        }
        if (input.length == 1) {
            return input[0] == sum;
        }
        if (sum == 0) {
            return true;
        }
        if (cache[currentIndex][sum] != null) {
            return cache[currentIndex][sum];
        }
        counter.increment();
        return cache[currentIndex][sum] = subsetExistsRecursive(input, sum - input[currentIndex], currentIndex + 1, cache)
                || subsetExistsRecursive(input, input[currentIndex], currentIndex + 1, cache);

    }

    @Test
    void test1(){
        Assertions.assertTrue(subsetExists(new int[]{1,2,3,7},6));
    }

    @Test
    void test2(){
        Assertions.assertTrue(subsetExists(new int[]{1, 2, 7, 1, 5},10));
    }

    @Test
    void test3(){
        Assertions.assertFalse(subsetExists(new int[]{1,3,4,8},6));
        System.out.println("Total # of recursion call : "+ counter.getCounter());
    }
}
